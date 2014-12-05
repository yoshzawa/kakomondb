package kakomon3.jdo;

import java.util.ArrayList;
import java.util.List;

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
	private String genre;

	@Persistent
	private List<Long> tags;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public List<Long> getTags() {
		return tags;
	}

	public void setTags(List<Long> tags) {
		this.tags = tags;
	}
	
	public void addTags(Long id) {
		List<Long> list = getTags();
		list.add(id);
		setTags(list);
	}	

	public Mondai(String URL, String comment, Genre genre) {
		setURL(URL);
		setComment(comment);
		setGenre(genre.getId());
		genre.addMondais(URL);
		setTags(new ArrayList<Long>());
	}

	public static void init(PersistenceManager pm) {

		Genre g1_01 = pm.getObjectById(Genre.class, "1-01");

		pm.makePersistent(new Mondai("ap/APH240401.png", "応用情報平成24年度春問01",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH240402.png", "応用情報平成24年度春問02",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH240403.png", "応用情報平成24年度春問03",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH240404.png", "応用情報平成24年度春問04",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH240405.png", "応用情報平成24年度春問05",
				g1_01));

		pm.makePersistent(new Mondai("ap/APH241001.png", "応用情報平成24年度秋問01",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH241002.png", "応用情報平成24年度秋問02",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH241003.png", "応用情報平成24年度秋問03",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH241004.png", "応用情報平成24年度秋問04",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH241005.png", "応用情報平成24年度秋問05",
				g1_01));

		pm.makePersistent(new Mondai("ap/APH250401.png", "応用情報平成25年度春問01",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH250402.png", "応用情報平成25年度春問02",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH250403.png", "応用情報平成25年度春問03",
				g1_01));
		pm.makePersistent(new Mondai("ap/APH250404.png", "応用情報平成25年度春問04",
				g1_01));

	}
	
	public static List<Mondai> findAll(PersistenceManager pm){
		Query query = pm.newQuery(Mondai.class);
		@SuppressWarnings("unchecked")
		List<Mondai> list = (List<Mondai>) query.execute();
		return list;
	}
}
