package kakomon3.jdo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.JDOFatalUserException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.*;

@PersistenceCapable
public class Kaiin {

	@PrimaryKey
	@Persistent
	private String mail;

	@Persistent
	private User user;

	@Persistent(mappedBy = "kaiin")
	private List<KaiinGenre> kaiinGenreList;

	@Persistent
	private List<String> genreId;

	@Persistent
	private Date created;

	public List<KaiinGenre> getKaiinGenreList() {
		return kaiinGenreList;
	}

	public void setKaiinGenreList(List<KaiinGenre> kaiinGenreList) {
		this.kaiinGenreList = kaiinGenreList;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getGenreId() {
		return genreId;
	}

	public void setGenreId(List<String> genreId) {
		this.genreId = genreId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Kaiin(User user) {
		setMail(user.getEmail());
		setUser(user);
		setCreated(new java.util.Date());
		KaiinGenre kg = new KaiinGenre("1-01",this);
		ArrayList<KaiinGenre> list = new ArrayList<KaiinGenre>();
		list.add(kg);
		setKaiinGenreList(list);
		
	}

	private KaiinGenre getByGenreId(PersistenceManager pm, String genreId) {
		List<KaiinGenre> list = getKaiinGenreList();
		if ((list != null) && (list.size() > 0)) {
			for (KaiinGenre kaiinGenre : list) {
				if (kaiinGenre.getGenreId().equals(genreId)) {
					return kaiinGenre;
				}
			}
		}
		return null;
	}

	public int[] addWinMondaiIdMap(PersistenceManager pm, String genreId,
			String mondaiId) {
		int i[] = new int[3];
		KaiinGenre kaiinGenre = getByGenreId(pm, genreId);
		if (kaiinGenre != null) {
			Set<String> mondaiList = kaiinGenre.getWinMondaiIdMap();

			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				kaiinGenre.setWinMondaiIdMap(mondaiList);
				kaiinGenre.makePersistent(pm);
			}
			mondaiList = kaiinGenre.getLoseMondaiIdMap();
			if (mondaiList.contains(mondaiId) == true) {
				mondaiList.remove(mondaiId);
				kaiinGenre.setLoseMondaiIdMap(mondaiList);
				kaiinGenre.makePersistent(pm);
			}
			 mondaiList = kaiinGenre.getPendingMondaiIdMap();
			if (mondaiList.contains(mondaiId) == true) {
				mondaiList.remove(mondaiId);
				kaiinGenre.setPendingMondaiIdMap(mondaiList);
				kaiinGenre.makePersistent(pm);
			}
		}
		i = countMapAll();
		return i;
	}

	public int[] addLoseMondaiIdMap(PersistenceManager pm, String genreId,
			String mondaiId) {
		int i[] = new int[3];

		KaiinGenre kaiinGenre = getByGenreId(pm, genreId);
		{
			Set<String> mondaiList = kaiinGenre.getLoseMondaiIdMap();
			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				kaiinGenre.setLoseMondaiIdMap(mondaiList);
				makePersistent(pm);

			}
		}
		i = countMapAll();

		return i;
	}

	public int[] addPendingMondaiIdMap(PersistenceManager pm, String genreId,
			String mondaiId) {
		int i[] = new int[3];
		KaiinGenre kaiinGenre = getByGenreId(pm, genreId);
		{
			Set<String> mondaiList = kaiinGenre.getPendingMondaiIdMap();
			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				kaiinGenre.setPendingMondaiIdMap(mondaiList);
				makePersistent(pm);
			}
		}
		i = countMapAll();

		return i;
	}

	private int[] countMapAll() {
		int i[] = new int[3];
		i[0] = 0;
		i[1] = 0;
		i[2] = 0;
		List<KaiinGenre> list = getKaiinGenreList();
		if((list != null)&&(list.size()>0)){
			for (KaiinGenre k : list) {
				i[0] += k.getWinMondaiIdMap().size();
				i[1] += k.getLoseMondaiIdMap().size();
				i[2] += k.getPendingMondaiIdMap().size();
			}
		}
		return i;
	}

	public void makeMap(PersistenceManager pm) {
		List<Kaitou> list = Kaitou.getListByUser(pm, getUser());
		Map<String, Mondai> mondaiMap = Mondai.getMap(pm);
		for (Kaitou kaitou : list) {
			String mondaiId = kaitou.getMondaiId();
			Mondai mondai = mondaiMap.get(mondaiId);
			String genreId = mondai.getGenre();
			if (kaitou.isSeikai() == true) {
				addWinMondaiIdMap(pm, genreId, mondaiId);
			} else {
				addLoseMondaiIdMap(pm, genreId, mondaiId);
			}
		}
		makePersistent(pm);
	}

	public static Kaiin getById(PersistenceManager pm, User user) {
		try {
			Kaiin kaiin = (Kaiin) pm
					.getObjectById(Kaiin.class, user.getEmail());
			return kaiin;
		} catch (JDOObjectNotFoundException e) {
			Kaiin kaiin = new Kaiin(user);
			kaiin.makePersistent(pm);
			return kaiin;
		}
	}

	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
	}
}
