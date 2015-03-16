package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private Set<String> mondaiList;

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

	public Set<String> getMondaiList() {
		return mondaiList;
	}

	public void setMondaiList(Set<String> mondais) {
		this.mondaiList = mondais;
	}

	public void addMondais(String mondaiId) {
		Set<String> set = getMondaiList();
		set.add(mondaiId);
		setMondaiList(set);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondaiList(new HashSet<String>());
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
	public static Map<String, Genre> getMap(PersistenceManager pm) {
		List<Genre> list2 = Genre.getList(pm);
		return getMap(pm, list2);
	}

	public static Map<String, Genre> getMap(PersistenceManager pm,
			List<Genre> genreList) {

		Map<String, Genre> mapTag = new HashMap<String, Genre>();

		for (Genre g : genreList) {
			mapTag.put(g.getId(), g);
		}
		return mapTag;
	}

	public static Genre getById(PersistenceManager pm, String id) {
		return pm.getObjectById(Genre.class, id);
	}

	public Genre makePersistent(PersistenceManager pm) {
		Genre result = pm.makePersistent(this);
		return result;
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