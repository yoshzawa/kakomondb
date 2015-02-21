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

	@Persistent
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

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
		this(user, mondaiId, null, false, null);

	}

	public Kaitou(User user, String mondaiId, Sentaku sentaku, boolean seikai,
			Member member) {
		setUser(user);
		setMondaiId(mondaiId);
		setSentaku(sentaku);
		setSeikai(seikai);
		if (member != null) {
			member.addKaitouList(this);
			setMember(member);
		}
	}

	/**
	 * 指定されたkeyに該当するKaitouのインスタンスを返す
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @param key
	 *            KaitouのKey
	 * @return 該当するインスタンス
	 */
	public static Kaitou getById(PersistenceManager pm, Key key) {
		return pm.getObjectById(Kaitou.class, key);
	}

	public static Kaitou getById(PersistenceManager pm, String keyString) {
		Key key = KeyFactory.stringToKey(keyString);
		return getById(pm, key);
	}

	/**
	 * userを指定し、該当するKaitouのインスタンスを返す。
	 * 該当するuserの、未解答のKaitouがある場合はそれを返す（一番先頭にあるもの）。
	 * 未回答がない場合は、指定されたmondaiIdを利用したインスタンスを生成し、それを返す。
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 * @param user
	 *            解答するuser
	 * @param mondaiId
	 *            新規に答えたいMondaiのid
	 * @return 未解答のMondaiのインスタンス
	 */
	public static Kaitou getByUser(PersistenceManager pm, User user,
			String mondaiId) {
		Query query = pm.newQuery(Kaitou.class);
		query.setFilter("user == newUser && sentaku == null");
		query.setOrdering("key");
		query.declareParameters("com.google.appengine.api.users.User newUser");

		@SuppressWarnings("unchecked")
		List<Kaitou> result = (List<Kaitou>) query.execute(user);
		if (result.isEmpty()) {
			Kaitou k = new Kaitou(user, mondaiId);
			k.makePersistent(pm);
			return k;
		} else {
			return result.get(0);
		}
	}

	/**
	 * インスタンスを永続化する
	 * 
	 * @param pm
	 *            PersistenceManagerのインスタンス
	 */
	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
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

	public static List<Kaitou> getListByUser(PersistenceManager pm, User user) {

		Query query = pm.newQuery(Kaitou.class);
		query.setFilter("user == newUser");
		query.setOrdering("key");
		query.declareParameters("com.google.appengine.api.users.User newUser");

		@SuppressWarnings("unchecked")
		List<Kaitou> result = (List<Kaitou>) query.execute(user);
		return result;
	}

}
