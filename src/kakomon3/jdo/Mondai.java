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
public class Mondai {

	@PrimaryKey
	@Persistent
	private String URL;

	@Persistent
	private String comment;

	@Persistent
	private Kaitou kotae;

	@Persistent
	private String genre;

	@Persistent
	private List<String> tags;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Kaitou getKotae() {
		return kotae;
	}

	public void setKotae(Kaitou kotae) {
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

	public Mondai(String URL, String comment, Genre genre, Kaitou kotae) {
		setURL(URL);
		setComment(comment);
		setGenre(genre.getId());
		genre.addMondais(URL);
		setTags(new ArrayList<String>());
		setKotae(kotae);
	}

	public static void init(PersistenceManager pm) {

		Genre g1_01 = pm.getObjectById(Genre.class, "1-01");

		pm.makePersistent(new Mondai("ap/APH240401.png", "応用情報平成24春季午前問01",
				g1_01, Kaitou.a));
		pm.makePersistent(new Mondai("ap/APH240402.png", "応用情報平成24春季午前問02",
				g1_01, Kaitou.e));
		pm.makePersistent(new Mondai("ap/APH240403.png", "応用情報平成24春季午前問03",
				g1_01, Kaitou.u));
		pm.makePersistent(new Mondai("ap/APH240404.png", "応用情報平成24春季午前問04",
				g1_01, Kaitou.a));
		pm.makePersistent(new Mondai("ap/APH240405.png", "応用情報平成24秋季午前問05",
				g1_01, Kaitou.a));

		pm.makePersistent(new Mondai("ap/APH241001.png", "応用情報平成24秋季午前問01",
				g1_01,Kaitou.a));
		pm.makePersistent(new Mondai("ap/APH241002.png", "応用情報平成24秋季午前問02",
				g1_01,Kaitou.i));
		pm.makePersistent(new Mondai("ap/APH241003.png", "応用情報平成24秋季午前問03",
				g1_01,Kaitou.e));
		pm.makePersistent(new Mondai("ap/APH241004.png", "応用情報平成24秋季午前問04",
				g1_01,Kaitou.i));
		pm.makePersistent(new Mondai("ap/APH241005.png", "応用情報平成24秋季午前問05",
				g1_01,Kaitou.a));

		pm.makePersistent(new Mondai("ap/APH250401.png", "応用情報平成25秋季午前問01",
				g1_01,Kaitou.i));
		pm.makePersistent(new Mondai("ap/APH250402.png", "応用情報平成25秋季午前問02",
				g1_01,Kaitou.a));
		pm.makePersistent(new Mondai("ap/APH250403.png", "応用情報平成25秋季午前問03",
				g1_01,Kaitou.u));
		pm.makePersistent(new Mondai("ap/APH250404.png", "応用情報平成25秋季午前問04",
				g1_01,Kaitou.a));

	}

	public static List<Mondai> findAll(PersistenceManager pm) {
		Query query = pm.newQuery(Mondai.class);
		@SuppressWarnings("unchecked")
		List<Mondai> list = (List<Mondai>) query.execute();
		return list;
	}

	public static Map<String, Mondai> getMapAll(PersistenceManager pm) {
		List<Mondai> list = Mondai.findAll(pm);

		Map<String, Mondai> map = new HashMap<String, Mondai>();

		for (Mondai g : list) {
			map.put(g.getURL(), g);
		}
		return map;
	}
}
