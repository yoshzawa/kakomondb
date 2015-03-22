package kakomon3.jdo;

import java.io.Serializable;
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

import kakomon3.jdo.cache.GenreCache;

@PersistenceCapable
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697303811337883999L;

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

	public Set<String> getMondais() {
		return mondaiList;
	}

	public void setMondais(Set<String> mondais) {
		this.mondaiList = mondais;
	}

	public void addMondais(String mondaiId) {
		Set<String> set = getMondais();
		set.add(mondaiId);
		setMondais(set);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondais(new HashSet<String>());
	}

	/**
	 * Genre全件をList<Genre>で取得する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return 全件分のList
	 */
	public static List<Genre> getList(PersistenceManager pm, boolean useCache) {
		if (useCache == true) {
			return GenreCache.getList(pm);
		} else {
			Query query = pm.newQuery(Genre.class);
			@SuppressWarnings("unchecked")
			List<Genre> list = (List<Genre>) query.execute();
			return list;
		}
	}

	public static List<Genre> getList(PersistenceManager pm) {
		return (getList(pm, true));
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

	public static Genre getById(PersistenceManager pm, String id,
			boolean useCache) {
		if (useCache == true) {
			Genre genre = GenreCache.getById(pm, id);
			return genre;
		} else {
			return pm.getObjectById(Genre.class, id);
		}
	}

	public static Genre getById(PersistenceManager pm, String id) {
		// return pm.getObjectById(Genre.class, id);
		return getById(pm, id, true);
	}

	public Genre makePersistent(PersistenceManager pm, boolean useCache) {
		Genre result;
		if (useCache == true) {
			result = GenreCache.makePersistent(pm, this);
		} else {
			result = pm.makePersistent(this);
		}
		return result;
	}

	public Genre makePersistent(PersistenceManager pm) {
		return makePersistent(pm, true);
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