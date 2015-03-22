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

	private static void toCache(PersistenceManager pm, MemcacheService memcache) {
		boolean containList = memcache.contains(keyListStr);

		List<Genre> genreList;
		if (containList == false) {
			genreList = Genre.getList(pm, false);
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
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService("GenreCache");
		toCache(pm, memcache);

		boolean contains = memcache.contains(id);
		Genre genre = null;
		if (contains == true) {
			genre = (Genre) memcache.get(id);
		}
		return genre;
	}

	@SuppressWarnings("unchecked")
	public static List<Genre> getList(PersistenceManager pm) {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService("GenreCache");
		toCache(pm, memcache);
		List<Genre> genreList;
		Set<String> keyList = (Set<String>) memcache.get(keyListStr);
		genreList = new ArrayList<Genre>();
		for (String id : keyList) {
			genreList.add((Genre) memcache.get(id));
		}

		return genreList;
	}

	@SuppressWarnings("unchecked")
	public static Genre makePersistent(PersistenceManager pm, Genre genre) {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService("GenreCache");
		toCache(pm, memcache);
		String id = genre.getId();
		memcache.put(id, genre);
		Set<String> keyList = (Set<String>) memcache.get(keyListStr);
		keyList.add(id);
		memcache.put(keyListStr, keyList);
		genre.makePersistent(pm, false);
		return genre;
	}
}
