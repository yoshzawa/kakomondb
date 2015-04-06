package kakomon3.jdo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import kakomon3.PersonalData;
import kakomon3.jdo.jdostatic.MemberStatic;

import com.google.appengine.api.users.User;

@PersistenceCapable
public class Member extends MemberStatic {

	@PrimaryKey
	@Persistent
	private String mail;

	@Persistent
	private User user;

	@Persistent(mappedBy = "member")
	private List<MemberGenre> memberGenreList;

	@Persistent
	private Date created;

	@Persistent
	private Date modified;

	@Persistent
	private int exp;

	@Persistent
	private int coin;

	public Member(User user) {
		setMail(user.getEmail());
		setUser(user);
		setCreated(new java.util.Date());
		setModified(modified);
		setExp(0);
		setCoin(100);
		MemberGenre kg = new MemberGenre(PersonalData.defaultGenreId, this);
		ArrayList<MemberGenre> list = new ArrayList<MemberGenre>();
		list.add(kg);
		setKaiinGenreList(list);
	}

	final public Date getModified() {
		return modified;
	}

	final public void setModified(Date modified) {
		this.modified = modified;
	}

	final public int getExp() {
		return exp;
	}

	final public void setExp(int exp) {
		this.exp = exp;
	}

	final public int addExp(int exp) {
		exp += getExp();
		setExp(exp);
		return exp;
	}

	final public int getCoin() {
		return coin;
	}

	final public void setCoin(int coin) {
		this.coin = coin;
	}

	final public int addCoin(int coin) {
		coin += getCoin();
		setCoin(coin);
		return coin;
	}

	final public List<MemberGenre> getMemberGenreList() {
		return memberGenreList;
	}

	final public Map<String, MemberGenre> getMemberGenreMap() {
		Map<String, MemberGenre> memberGenreMap = new HashMap<String, MemberGenre>();
		for (MemberGenre mg : getMemberGenreList()) {
			memberGenreMap.put(mg.getGenreId(), mg);
		}
		return memberGenreMap;
	}

	final public void setMemberGenreList(List<MemberGenre> memberGenreList) {
		this.memberGenreList = memberGenreList;
	}

	final public List<MemberGenre> getKaiinGenreList() {
		return memberGenreList;
	}

	final public void setKaiinGenreList(List<MemberGenre> memberGenreList) {
		this.memberGenreList = memberGenreList;
	}

	final public String getMail() {
		return mail;
	}

	final public void setMail(String mail) {
		this.mail = mail;
	}

	final public User getUser() {
		return user;
	}

	final public void setUser(User user) {
		this.user = user;
	}

	final public Date getCreated() {
		return created;
	}

	final public void setCreated(Date created) {
		this.created = created;
	}

	final private MemberGenre getByGenreId(PersistenceManager pm, String genreId) {
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

	final public int[] addWinMondaiIdSet(PersistenceManager pm, String genreId,
			String mondaiId) {
		int i[] = new int[3];
		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
		if (kaiinGenre != null) {
			kaiinGenre.addWinMondaiIdSet(mondaiId);
			kaiinGenre.removeLoseMondaiIdSet(mondaiId);
			kaiinGenre.removePendingMondaiIdSet(mondaiId);
			kaiinGenre.makePersistent(pm);
		}
		i = countMapAll();
		return i;
	}

	final public int[] addLoseMondaiIdSet(PersistenceManager pm,
			String genreId, String mondaiId) {
		int i[] = new int[3];

		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
		if (kaiinGenre != null) {
			kaiinGenre.addLoseMondaiIdSet(mondaiId);
			makePersistent(pm);
		}
		i = countMapAll();

		return i;
	}

	final public int[] addPendingMondaiIdSet(PersistenceManager pm,
			String genreId, String mondaiId) {
		int i[] = new int[3];
		MemberGenre kaiinGenre = getByGenreId(pm, genreId);
		if (kaiinGenre != null) {
			kaiinGenre.addPendingMondaiIdSet(mondaiId);
			makePersistent(pm);
		}
		i = countMapAll();

		return i;
	}

	final private int[] countMapAll() {
		int i[] = new int[3];
		i[0] = 0;
		i[1] = 0;
		i[2] = 0;
		List<MemberGenre> list = getKaiinGenreList();
		if ((list != null) && (list.size() > 0)) {
			for (MemberGenre k : list) {
				i[0] += k.getWinMondaiIdSet().size();
				i[1] += k.getLoseMondaiIdSet().size();
				i[2] += k.getPendingMondaiIdSet().size();
			}
		}
		return i;
	}

	final public void makeMap(PersistenceManager pm) {
		List<Kaitou> list = Kaitou.getListByUser(pm, getUser());
		Map<String, Mondai> mondaiMap = Mondai.getMap(pm);
		for (Kaitou kaitou : list) {
			String mondaiId = kaitou.getMondaiId();
			Mondai mondai = mondaiMap.get(mondaiId);
			String genreId = mondai.getGenre();
			if (kaitou.isSeikai() == true) {
				addWinMondaiIdSet(pm, genreId, mondaiId);
			} else {
				addLoseMondaiIdSet(pm, genreId, mondaiId);
			}
		}
		makePersistent(pm);
	}

	final public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
	}

	final public List<String> getGenreList() {
		List<String> genreList = new ArrayList<>();
		List<MemberGenre> l = getKaiinGenreList();
		if ((l != null) && (l.size() > 0)) {
			for (MemberGenre kg : l) {
				genreList.add(kg.getGenreId());
			}
		}
		return genreList;
	}

	final public Map<String, MemberGenre> getKaiinGenreMap() {
		Map<String, MemberGenre> list = new HashMap<String, MemberGenre>();
		List<MemberGenre> l = getKaiinGenreList();
		for (MemberGenre k : l) {
			list.put(k.getGenreId(), k);
		}
		return list;
	}

}