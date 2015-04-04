package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import kakomon3.jdo.cache.GenreCache;

public class GenreStatic {

	protected static final boolean useCache = false;

	/**
	 * Genre全件をList<Genre>で取得する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return 全件分のList
	 */
	public static final List<Genre> getList(PersistenceManager pm,
			boolean useCache) {
		if (useCache == true) {
			return GenreCache.getList(pm);
		} else {
			Query query = pm.newQuery(Genre.class);
			@SuppressWarnings("unchecked")
			List<Genre> list = (List<Genre>) query.execute();
			return list;
		}
	}

	public static final List<Genre> getList(PersistenceManager pm) {
		return (getList(pm, useCache));
	}

	/**
	 * Genre全件のidとnameのMap<String id,String name>を取得する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return Map
	 */
	public static final Map<String, Genre> getMap(PersistenceManager pm) {
		List<Genre> list2 = Genre.getList(pm);
		return getMap(pm, list2);
	}

	public static final Map<String, Genre> getMap(PersistenceManager pm,
			List<Genre> genreList) {

		Map<String, Genre> mapTag = new HashMap<String, Genre>();

		for (Genre g : genreList) {
			String genreId = g.getId();
			mapTag.put(genreId, g);
		}
		return mapTag;
	}

	public static final Genre getById(PersistenceManager pm, String id) {
		return getById(pm, id, useCache);
	}

	public static final Genre getById(PersistenceManager pm, String id,
			boolean useCache) {
		if (useCache == true) {
			Genre genre = GenreCache.getById(pm, id);
			return genre;
		} else {
			try {
				return pm.getObjectById(Genre.class, id);
			} catch (javax.jdo.JDOObjectNotFoundException e) {
				return null;
			}
		}
	}

	public static final void init(PersistenceManager pm) {
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] { "1-01", "基礎理論" });
		list.add(new String[] { "1-02", "アルゴリズムとプログラミング" });
		list.add(new String[] { "2-03", "コンピュータ構成要素" });

		for (String[] s : list) {
			Genre g = new Genre(s[0], s[1]);
			g.makePersistent(pm);
		}
	}

}
