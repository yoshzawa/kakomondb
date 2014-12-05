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
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	@Persistent
	private List<String> mondais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

		mondai.addTags(getId());
	}

	public Tag(String name) {
		setName(name);
		setMondais(new ArrayList<String>());
	}

	public static List<Tag> findAll(PersistenceManager pm){
		Query query = pm.newQuery(Tag.class);
		@SuppressWarnings("unchecked")
		List<Tag> list = (List<Tag>) query.execute();
		return list;
	}
	
	public static Map<Long,String> getMapAll(PersistenceManager pm){
		List<Tag> list2 = Tag.findAll(pm);

		Map<Long, String> map = new HashMap<Long, String>();

		for (Tag g : list2) {
			map.put(g.getId(), g.getName());
		}
		return map;
	}
	
	public static void init(PersistenceManager pm) {

		Tag t = new Tag("ÉuÅ[ÉãââéZ");
		pm.makePersistent(t);

		String[] mondais = { "ap/APH240401.png", "ap/APH241001.png",
				"ap/APH241003.png", "ap/APH250401.png", "ap/APH250402.png",
				"ap/APH250404.png" };
		for (String s : mondais) {
			Mondai m = pm.getObjectById(Mondai.class, s);
			t.addMondais(m);
			pm.makePersistent(m);
		}
	
	Tag t2 = new Tag("BNF");
	pm.makePersistent(t2);

	String[] mondais2 = { "ap/APH240403.png" };
	for (String s : mondais2) {
		Mondai m = pm.getObjectById(Mondai.class, s);
		t2.addMondais(m);
		pm.makePersistent(m);
	}

	Tag t3 = new Tag("åÎÇËí˘ê≥");
	pm.makePersistent(t3);

	String[] mondais3 = { "ap/APH240405.png","ap/APH241003.png","ap/APH250404.png" };
	for (String s : mondais3) {
		Mondai m = pm.getObjectById(Mondai.class, s);
		t3.addMondais(m);
		pm.makePersistent(m);
	}

	
	}
	

}
