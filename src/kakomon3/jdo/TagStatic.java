package kakomon3.jdo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class TagStatic {
	public static List<Tag> getList(PersistenceManager pm) {
		Query query = pm.newQuery(Tag.class);
		@SuppressWarnings("unchecked")
		List<Tag> list = (List<Tag>) query.execute();
		return list;
	}

	public static Map<String, Tag> getMap(PersistenceManager pm){
		List<Tag> list = getList(pm);
		return getMap(pm,list);
	}

	public static Map<String, Tag> getMap(PersistenceManager pm,
			List<Tag> tagList) {

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : tagList) {
			map.put(g.getName(), g);
		}
		return map;
	}
	

	public static void init(PersistenceManager pm) {

		Tag t1 = new Tag("ブール演算");
		String[] mondais1 = { "APH240401", "APH241001", "APH241003",
				"APH250401", "APH250402", "APH250404" };
		init2(pm, t1, mondais1);

		Tag t2 = new Tag("BNF");
		String[] mondais2 = { "APH240403" };
		init2(pm, t2, mondais2);

		Tag t3 = new Tag("誤り訂正");
		String[] mondais3 = { "APH240405", "APH241003", "APH250404" };
		init2(pm, t3, mondais3);

		Tag t4 = new Tag("オートマトン");
		String[] mondais4 = { "APH250403" };
		init2(pm, t4, mondais4);

		Tag t5 = new Tag("配列");
		String[] mondais5 = { "APH241005" };
		init2(pm, t5, mondais5);

		String[] mondais6 = { "APH241005" };
		init2(pm, new Tag("スタック"), mondais6);

		Tag t7 = new Tag("桁数");
		String[] mondais7 = { "APH250401" };
		init2(pm, t7, mondais7);

	}

	private static void init2(PersistenceManager pm, Tag t, String[] mondais) {
		pm.makePersistent(t);
		for (String s : mondais) {
			Mondai m = pm.getObjectById(Mondai.class, s);
			t.addMondais(m);
			m.makePersistent(pm);
		}
	}

}
