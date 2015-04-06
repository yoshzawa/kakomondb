package kakomon3.jdo.jdostatic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;

import kakomon3.jdo.Genre;
import kakomon3.jdo.Mondai;
import kakomon3.jdo.Sentaku;
import kakomon3.jdo.cache.MondaiCache;

public class MondaiStatic extends StaticCommon {

	protected static final boolean useCache = false;

	public static final List<Mondai> getList(PersistenceManager pm,
			boolean useCache) {
		if (useCache == true) {
			return MondaiCache.getList(pm);
		} else {
			return getList(pm, Mondai.class);

		}
	}

	public static final List<Mondai> getList(PersistenceManager pm) {
		return (getList(pm, useCache));
	}

	public static void init(PersistenceManager pm) {

		Genre g1_01 = pm.getObjectById(Genre.class, "1-01");

		pm.makePersistent(new Mondai("APH240401", "応用情報平成24春季午前問01", g1_01,
				Sentaku.a));
		pm.makePersistent(new Mondai("APH240402", "応用情報平成24春季午前問02", g1_01,
				Sentaku.e));
		pm.makePersistent(new Mondai("APH240403", "応用情報平成24春季午前問03", g1_01,
				Sentaku.u));
		pm.makePersistent(new Mondai("APH240404", "応用情報平成24春季午前問04", g1_01,
				Sentaku.a));
		pm.makePersistent(new Mondai("APH240405", "応用情報平成24秋季午前問05", g1_01,
				Sentaku.a));

		pm.makePersistent(new Mondai("APH241001", "応用情報平成24秋季午前問01", g1_01,
				Sentaku.a));
		pm.makePersistent(new Mondai("APH241002", "応用情報平成24秋季午前問02", g1_01,
				Sentaku.i));
		pm.makePersistent(new Mondai("APH241003", "応用情報平成24秋季午前問03", g1_01,
				Sentaku.e));
		pm.makePersistent(new Mondai("APH241004", "応用情報平成24秋季午前問04", g1_01,
				Sentaku.i));
		pm.makePersistent(new Mondai("APH241005", "応用情報平成24秋季午前問05", g1_01,
				Sentaku.a));

		pm.makePersistent(new Mondai("APH250401", "応用情報平成25秋季午前問01", g1_01,
				Sentaku.i));
		pm.makePersistent(new Mondai("APH250402", "応用情報平成25秋季午前問02", g1_01,
				Sentaku.a));
		pm.makePersistent(new Mondai("APH250403", "応用情報平成25秋季午前問03", g1_01,
				Sentaku.u));
		pm.makePersistent(new Mondai("APH250404", "応用情報平成25秋季午前問04", g1_01,
				Sentaku.a));

	}

	public static final Map<String, Mondai> getMap(PersistenceManager pm) {
		List<Mondai> list = Mondai.getList(pm);
		return getMap(pm, list);
	}

	public static final Map<String, Mondai> getMap(PersistenceManager pm,
			List<Mondai> mondaiList) {

		Map<String, Mondai> map = new HashMap<String, Mondai>();

		for (Mondai g : mondaiList) {
			String mondaiId = g.getId();
			map.put(mondaiId, g);
		}
		return map;
	}

	public static final Mondai getById(PersistenceManager pm, String id,
			boolean useCache) {
		if (useCache == true) {
			Mondai mondai = MondaiCache.getById(pm, id);
			return mondai;
		} else {
			try {
				Mondai obj = pm.getObjectById(Mondai.class, id);
				return obj;
			} catch (javax.jdo.JDOObjectNotFoundException e) {
				return null;
			}
		}
	}

	public static final Mondai getById(PersistenceManager pm, String id) {
		return getById(pm, id, useCache);
	}

}
