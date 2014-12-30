package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Tag {

	@PrimaryKey
	@Persistent
	private String name;

	@Persistent
	private List<String> mondais;

	// public Long getId() {
	// return id;
	// }

	// public void setId(Long id) {
	// this.id = id;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMondais() {
		return mondais;
	}

	public void setMondais(List<String> mondais) {
		this.mondais = mondais;
	}

	public void addMondais(Mondai mondai) {
		String mondaiURL = mondai.getURL();

		List<String> list = getMondais();
		list.add(mondaiURL);
		setMondais(list);

		// mondai.addTags(getId());
		mondai.addTags(getName());
	}

	public Tag(String name) {
		setName(name);
		setMondais(new ArrayList<String>());
	}

	public static List<Tag> findAll(PersistenceManager pm) {
		Query query = pm.newQuery(Tag.class);
		@SuppressWarnings("unchecked")
		List<Tag> list = (List<Tag>) query.execute();
		return list;
	}

	public static Map<String, Tag> getMapAll(PersistenceManager pm) {
		List<Tag> list2 = Tag.findAll(pm);

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : list2) {
			map.put(g.getName(), g);
		}
		return map;
	}

	public static void init(PersistenceManager pm) {

		Tag t1 = new Tag("ブール演算");
		String[] mondais1 = { "ap/APH240401.png", "ap/APH241001.png",
				"ap/APH241003.png", "ap/APH250401.png", "ap/APH250402.png",
				"ap/APH250404.png" };
		init2(pm, t1, mondais1);

		Tag t2 = new Tag("BNF");
		String[] mondais2 = { "ap/APH240403.png" };
		init2(pm, t2, mondais2);

		Tag t3 = new Tag("誤り訂正");
		String[] mondais3 = { "ap/APH240405.png", "ap/APH241003.png",
				"ap/APH250404.png" };
		init2(pm, t3, mondais3);

		Tag t4 = new Tag("オートマトン");
		String[] mondais4 = { "ap/APH250403.png" };
		init2(pm, t4, mondais4);

		Tag t5 = new Tag("配列");
		String[] mondais5 = { "ap/APH241005.png" };
		init2(pm, t5, mondais5);

		String[] mondais6 = { "ap/APH241005.png" };
		init2(pm, new Tag("スタック"), mondais6);

		Tag t7 = new Tag("桁数");
		String[] mondais7 = { "ap/APH250401.png" };
		init2(pm, t7, mondais7);

	}

	private static void init2(PersistenceManager pm, Tag t, String[] mondais) {
		pm.makePersistent(t);
		for (String s : mondais) {
			Mondai m = pm.getObjectById(Mondai.class, s);
			t.addMondais(m);
			pm.makePersistent(m);
		}
	}

}
