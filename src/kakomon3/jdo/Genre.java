package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Genre全件をList<Genre>で取得する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return 全件分のList
	 */
	public static List<Genre> getList(PersistenceManager pm) {
		Query query = pm.newQuery(Genre.class);
		@SuppressWarnings("unchecked")
		List<Genre> list = (List<Genre>) query.execute();
		return list;
	}

	/**
	 * Genre全件のidとnameのMap<String id,String name>を取得する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return Map
	 */
	public static Map<String, String> getMap(PersistenceManager pm) {
		List<Genre> list2 = Genre.getList(pm);
		return getMap(pm,list2);
	}
	public static Map<String, String> getMap(PersistenceManager pm,List<Genre> genreList) {

		Map<String, String> mapTag = new HashMap<String, String>();

		for (Genre g : genreList) {
			mapTag.put(g.getId(), g.getName());
		}
		return mapTag;
	}
	

	public static Genre getById(PersistenceManager pm, String id) {
		return pm.getObjectById(Genre.class, id);
	}
	
	public void makePersistent(PersistenceManager pm){
		Genre result = pm.makePersistent(this);
	}

	public static void init(PersistenceManager pm) {
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