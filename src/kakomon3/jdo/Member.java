package kakomon3.jdo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class Member {

	@PrimaryKey
	@Persistent
	private String mail;

	@Persistent
	private User user;

	@Persistent(mappedBy = "member")
	private List<MemberGenre> memberGenreList;

	@Persistent
	private List<Key> kaitoukeyList;

	@Persistent
	private Date created;

	public List<MemberGenre> getMemberGenreList() {
		return memberGenreList;
	}

	public void setMemberGenreList(List<MemberGenre> memberGenreList) {
		this.memberGenreList = memberGenreList;
	}

	public List<Key> getKaitoukeyList() {
		return kaitoukeyList;
	}

	public void setKaitoukeyList(List<Key> kaitoukeyList) {
		this.kaitoukeyList = kaitoukeyList;
	}

	public List<MemberGenre> getKaiinGenreList() {
		return memberGenreList;
	}

	public void setKaiinGenreList(List<MemberGenre> memberGenreList) {
		this.memberGenreList = memberGenreList;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Member(User user) {
		setMail(user.getEmail());
		setUser(user);
		setCreated(new java.util.Date());
		MemberGenre kg = new MemberGenre("1-01", this);
		ArrayList<MemberGenre> list = new ArrayList<MemberGenre>();
		list.add(kg);
		setKaiinGenreList(list);

	}

	private MemberGenre getByGenreId(PersistenceManager pm, String genreId) {
		List<MemberGenre> list = getKaiinGenreList();
		if ((list != null) && (list.size() > 0)) {
			for (MemberGenre kaiinGenre : list) {
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
		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
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

		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
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
		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
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
		List<MemberGenre> list = getKaiinGenreList();
		if ((list != null) && (list.size() > 0)) {
			for (MemberGenre k : list) {
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

	public static Member getById(PersistenceManager pm, User user) {
		try {
			Member kaiin = (Member) pm.getObjectById(Member.class,
					user.getEmail());
			return kaiin;
		} catch (JDOObjectNotFoundException e) {
			Member kaiin = new Member(user);
			kaiin.makePersistent(pm);
			return kaiin;
		}
	}

	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
	}

	public List<String> getGenreList() {
		List<String> genreList = new ArrayList<>();
		List<MemberGenre> l = getKaiinGenreList();
		if ((l != null) && (l.size() > 0)) {
			for (MemberGenre kg : l) {
				genreList.add(kg.getGenreId());
			}
		}
		return genreList;
	}

	public Map<String, MemberGenre> getKaiinGenreMap() {
		Map<String, MemberGenre> list = new HashMap<String, MemberGenre>();
		List<MemberGenre> l = getKaiinGenreList();
		if ((l != null) && (l.size() > 0)) {
			for (MemberGenre k : l) {
				list.put(k.getGenreId(), k);
			}
		}
		return list;
	}

	public void addKaitouList(Kaitou kaitou) {
		List<Key> list = getKaitoukeyList();
		list.add(kaitou.getKey());
		setKaitoukeyList(list);

	}
}
