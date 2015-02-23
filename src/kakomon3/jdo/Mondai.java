package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOFatalInternalException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Mondai {

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String comment;

	@Persistent
	private Sentaku kotae;

	@Persistent
	private String genre;

	@Persistent
	private List<String> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Sentaku getKotae() {
		return kotae;
	}

	public void setKotae(Sentaku kotae) {
		this.kotae = kotae;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> list) {
		this.tags = list;
	}

	public void addTags(String string) {
		List<String> list = getTags();
		list.add(string);
		setTags(list);
	}

	public Mondai(String id, String comment, Genre genre, Sentaku kotae) {
		setId(id);
		setComment(comment);
		setGenre(genre.getId());
		genre.addMondais(id);
		setTags(new ArrayList<String>());
		setKotae(kotae);
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

	public static List<Mondai> getList(PersistenceManager pm) {
		Query query = pm.newQuery(Mondai.class);
		@SuppressWarnings("unchecked")
		List<Mondai> list = (List<Mondai>) query.execute();
		return list;
	}

	public static Map<String, Mondai> getMap(PersistenceManager pm) {
		List<Mondai> list = Mondai.getList(pm);
		return getMap(pm,list);
	}
	
	public static Map<String, Mondai> getMap(PersistenceManager pm,List<Mondai> mondaiListist) {

		Map<String, Mondai> map = new HashMap<String, Mondai>();

		for (Mondai g : mondaiListist) {
			map.put(g.getId(), g);
		}
		return map;
	}

	public static Mondai getById(PersistenceManager pm, String id)
			throws JDOFatalInternalException {
		return pm.getObjectById(Mondai.class, id);

	}
	/**
	 * インスタンスを永続化する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return 
	 */
	public Mondai makePersistent(PersistenceManager pm) {
		Mondai result = pm.makePersistent(this);
		return(result);
	}
}
