package kakomon3.jdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import kakomon3.jdo.cache.MondaiCache;

@PersistenceCapable
public class Mondai extends MondaiStatic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227615161093816961L;

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

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getComment() {
		return comment;
	}

	public final void setComment(String comment) {
		this.comment = comment;
	}

	public final Sentaku getKotae() {
		return kotae;
	}

	public final void setKotae(Sentaku kotae) {
		this.kotae = kotae;
	}

	public final String getGenre() {
		return genre;
	}

	public final void setGenre(String genre) {
		this.genre = genre;
	}

	public final List<String> getTags() {
		return tags;
	}

	public final void setTags(List<String> list) {
		this.tags = list;
	}

	public final void addTags(String string) {
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

	/**
	 * インスタンスを永続化する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return
	 */

	public final Mondai makePersistent(PersistenceManager pm, boolean useCache) {
		Mondai result;
		if (useCache == true) {
			result = MondaiCache.makePersistent(pm, this);
		} else {
			result = pm.makePersistent(this);
		}
		return result;
	}

	public final Mondai makePersistent(PersistenceManager pm) {
		return makePersistent(pm, useCache);
	}

}
