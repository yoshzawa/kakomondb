package kakomon3.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Genre {

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String name;

	@Persistent
	private List<String> mondais;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void addMondais(String mondaiId) {
		List<String> list = getMondais();
		list.add(mondaiId);
		setMondais(list);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondais(new ArrayList<String>());
	}

	public static void init(PersistenceManager pm) {
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"1-01", "基礎理論"});
		list.add(new String[]{"1-02", "アルゴリズムとプログラミング"});
		list.add(new String[]{"2-03", "コンピュータ構成要素"});

		for(String[] s:list){
			Genre g=new Genre(s[0], s[1]); 
			pm.makePersistent(g);
		}
	}
	
	public static List<Genre> findAll(PersistenceManager pm){
		Query query = pm.newQuery(Genre.class);
		@SuppressWarnings("unchecked")
		List<Genre> list = (List<Genre>) query.execute();
		return list;
	}
}
