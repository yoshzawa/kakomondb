package kakomon3.jdo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class MondaiImage {

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String URL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public MondaiImage(String id, String URL) {
		setId(id);
		setURL(URL);
	}

	public static void init(PersistenceManager pm) {
		pm.makePersistent(new MondaiImage("APH240401", "ap/APH240401.png"));
		pm.makePersistent(new MondaiImage("APH240402", "ap/APH240402.png"));
		pm.makePersistent(new MondaiImage("APH240403", "ap/APH240403.png"));
		pm.makePersistent(new MondaiImage("APH240404", "ap/APH240404.png"));
		pm.makePersistent(new MondaiImage("APH240405", "ap/APH240405.png"));

		pm.makePersistent(new MondaiImage("APH241001", "ap/APH241001.png"));
		pm.makePersistent(new MondaiImage("APH241002", "ap/APH241002.png"));
		pm.makePersistent(new MondaiImage("APH241003", "ap/APH241003.png"));
		pm.makePersistent(new MondaiImage("APH241004", "ap/APH241004.png"));
		pm.makePersistent(new MondaiImage("APH241005", "ap/APH241005.png"));

		pm.makePersistent(new MondaiImage("APH250401", "ap/APH250401.png"));
		pm.makePersistent(new MondaiImage("APH250402", "ap/APH250402.png"));
		pm.makePersistent(new MondaiImage("APH250403", "ap/APH250403.png"));
		pm.makePersistent(new MondaiImage("APH250404", "ap/APH250404.png"));

	}

	public static List<MondaiImage> getList(PersistenceManager pm) {
		Query query = pm.newQuery(MondaiImage.class);
		@SuppressWarnings("unchecked")
		List<MondaiImage> list = (List<MondaiImage>) query.execute();
		return list;
	}

	public static Map<String, MondaiImage> getMap(PersistenceManager pm) {
		List<MondaiImage> list = MondaiImage.getList(pm);

		Map<String, MondaiImage> map = new HashMap<String, MondaiImage>();

		for (MondaiImage g : list) {
			map.put(g.getId(), g);
		}
		return map;
	}

	public static MondaiImage getById(PersistenceManager pm, String id) {
		return pm.getObjectById(MondaiImage.class, id);
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

}
