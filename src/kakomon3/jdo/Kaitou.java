package kakomon3.jdo;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import kakomon3.jdo.jdostatic.KaitouStatic;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class Kaitou extends KaitouStatic {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private User user;

	@Persistent
	private String mondaiId;

	@Persistent
	private Sentaku sentaku;

	@Persistent
	private boolean seikai;

	public final Key getKey() {
		return key;
	}

	public final void setKey(Key key) {
		this.key = key;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final String getMondaiId() {
		return mondaiId;
	}

	public final void setMondaiId(String mondaiId) {
		this.mondaiId = mondaiId;
	}

	public final Sentaku getSentaku() {
		return sentaku;
	}

	public final void setSentaku(Sentaku sentaku) {
		this.sentaku = sentaku;
	}

	public final boolean isSeikai() {
		return seikai;
	}

	public final void setSeikai(boolean seikai) {
		this.seikai = seikai;
	}

	public Kaitou(User user, String mondaiId) {
		this(user, mondaiId, null, false);

	}

	public Kaitou(User user, String mondaiId, Sentaku sentaku, boolean seikai) {
		setUser(user);
		setMondaiId(mondaiId);
		setSentaku(sentaku);
		setSeikai(seikai);
	}

	/**
	 * インスタンスを永続化する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @return
	 */
	public Kaitou makePersistent(PersistenceManager pm) {
		Kaitou result = pm.makePersistent(this);
		return (result);
	}

	/**
	 * KeyをStringに変換して返す
	 * 
	 * @return Stringに変換したKey
	 */
	public String getKeyString() {
		Key kk = getKey();
		String id = KeyFactory.keyToString(kk);
		return id;
	}

}
