package kakomon3.jdo.jdostatic;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import kakomon3.jdo.Kaitou;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;

public class KaitouStatic extends StaticCommon {
	public static List<Kaitou> getList(PersistenceManager pm) {
		return (List<Kaitou>) getList(pm, Kaitou.class);
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
