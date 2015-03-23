package kakomon3.jdo.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.PersistenceManager;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MondaiCache {

	public static final String keyListStr = "__keyList";
	public static final String memCacheName = "MondaiCache";

	private static void toCache(PersistenceManager pm, MemcacheService memcache) {
		boolean containList = memcache.contains(keyListStr);

		List<Mondai> mondaiList;
		if (containList == false) {
			mondaiList = Mondai.getList(pm, false);
			Set<String> keyList = new TreeSet<>();
			for (Mondai m : mondaiList) {
				String id = m.getId();
				keyList.add(id);
				memcache.put(id, m);
			}
			memcache.put(keyListStr, keyList);
		}
	}

	public static Mondai getById(PersistenceManager pm, String id) {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService(memCacheName);
		toCache(pm, memcache);

		boolean contains = memcache.contains(id);
		Mondai genre = null;
		if (contains == true) {
			genre = (Mondai) memcache.get(id);
		}
		return genre;
	}

	@SuppressWarnings("unchecked")
	public static List<Mondai> getList(PersistenceManager pm) {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService(memCacheName);
		toCache(pm, memcache);
		List<Mondai> genreList;
		Set<String> keyList = (Set<String>) memcache.get(keyListStr);
		genreList = new ArrayList<Mondai>();
		for (String id : keyList) {
			genreList.add((Mondai) memcache.get(id));
		}

		return genreList;
	}

	@SuppressWarnings("unchecked")
	public static Mondai makePersistent(PersistenceManager pm, Mondai genre) {
		MemcacheService memcache = MemcacheServiceFactory
				.getMemcacheService(memCacheName);
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
