package kakomon3.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@PersistenceCapable
public class Kaitou {

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

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMondaiId() {
		return mondaiId;
	}

	public void setMondaiId(String mondaiId) {
		this.mondaiId = mondaiId;
	}

	public Sentaku getSentaku() {
		return sentaku;
	}

	public void setSentaku(Sentaku sentaku) {
		this.sentaku = sentaku;
	}

	public boolean isSeikai() {
		return seikai;
	}

	public void setSeikai(boolean seikai) {
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

	public static Kaitou getById(PersistenceManager pm, Key key) {
		return pm.getObjectById(Kaitou.class, key);
	}

	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
	}

	public String getKeyString() {
		// Key kk = k.getKey();
		// String kaitouId = KeyFactory.keyToString(kk);
		Key kk = getKey();
		String id = KeyFactory.keyToString(kk);
		return id;
	}

	public static Kaitou getByUser(PersistenceManager pm, User user,
			String mondaiId) {
		Query query = pm.newQuery(Kaitou.class);
		query.setFilter("user == newUser && sentaku == null");
		query.setOrdering("key");
		query.declareParameters("com.google.appengine.api.users.User newUser");

		List<Kaitou> result = (List<Kaitou>) query.execute(user);
		if (result.isEmpty()) {
			Kaitou k;
			k = new Kaitou(user, mondaiId);
			k.makePersistent(pm);
			return k;
		} else {
			return result.get(0);
		}

	}

}
