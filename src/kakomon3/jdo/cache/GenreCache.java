package kakomon3.jdo.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.PersistenceManager;

import kakomon3.jdo.Genre;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class GenreCache {

	public static final String keyListStr = "__keyList";
	public static final String memCacheName = "GenreCache";

	private static void toCache(PersistenceManager pm, MemcacheService memcache) {

		boolean containList = memcache.contains(keyListStr);

		if (containList == false) {
			List<Genre> genreList = Genre.getList(pm, false);
			Set<String> keyList = new TreeSet<>();
			for (Genre g : genreList) {
				String id = g.getId();
				keyList.add(id);
				memcache.put(id, g);
			}
			memcache.put(keyListStr, keyList);
		}
	}

	public static Genre getById(PersistenceManager pm, String id) {
		MemcacheService memcache = getMemcacheService();
		toCache(pm, memcache);

		boolean contains = memcache.contains(id);
		Genre genre = null;
		if (contains == true) {
			genre = (Genre) memcache.get(id);
		}
		return genre;
	}

	public static List<Genre> getList(PersistenceManager pm) {
		MemcacheService memcache = getMemcacheService();
		toCache(pm, memcache);
		List<Genre> genreList = new ArrayList<Genre>();

		Set<String> keyList = getKeySet(memcache);
		for (String id : keyList) {
			Genre g = (Genre) memcache.get(id);
			genreList.add(g);
		}
		
		System.out.println("GENRE SIZE:" + genreList.size());

		return genreList;
	}

	public static Genre makePersistent(PersistenceManager pm, Genre genre) {
		MemcacheService memcache = getMemcacheService();
		toCache(pm, memcache);
		
		String id = genre.getId();
		memcache.put(id, genre);
		addKeySet(memcache, id);
		genre.makePersistent(pm, false);
		return genre;
	}

	private static void addKeySet(MemcacheService memcache, String id) {
		Set<String> keyList = getKeySet(memcache);
		keyList.add(id);
		memcache.put(keyListStr, keyList);
	}

	@SuppressWarnings("unchecked")
	private static Set<String> getKeySet(MemcacheService memcache) {
		Set<String> keyList = (Set<String>) memcache.get(keyListStr);
		return keyList;
	}

	private static MemcacheService getMemcacheService() {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService(memCacheName);
		return memcache;
	}
}
