package kakomon3.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
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

	}

}
