package kakomon3.jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class MemberGenre {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Member member;

	@Persistent
	private String genreId;

	@Persistent
	private Set<String> winMondaiIdSet;

	@Persistent
	private Set<String> loseMondaiIdSet;

	@Persistent
	private Set<String> pendingMondaiIdSet;

	public MemberGenre(String genreId, Member kaiin) {
		setKaiin(kaiin);
		setGenreId(genreId);
		setWinMondaiIdSet(new HashSet<String>());
		setLoseMondaiIdSet(new HashSet<String>());
		setPendingMondaiIdSet(new HashSet<String>());
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Member getKaiin() {
		return member;
	}

	public void setKaiin(Member kaiin) {
		this.member = kaiin;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public Set<String> getWinMondaiIdSet() {
		return winMondaiIdSet;
	}

	public void setWinMondaiIdSet(Set<String> winMondaiIdMap) {
		this.winMondaiIdSet = winMondaiIdMap;
	}

	public Set<String> addWinMondaiIdSet(String mondaiId) {
		Set<String> set = getWinMondaiIdSet();
		if (set.contains(mondaiId) == false) {
			set.add(mondaiId);
			setWinMondaiIdSet(set);
		}
		return set;
	}

	public Set<String> removeWinMondaiIdSet(String mondaiId) {
		Set<String> set = getWinMondaiIdSet();
		if (set.contains(mondaiId) == true) {
			set.remove(mondaiId);
			setWinMondaiIdSet(set);
		}
		return set;
	}

	public Set<String> getLoseMondaiIdSet() {
		return loseMondaiIdSet;
	}

	public void setLoseMondaiIdSet(Set<String> loseMondaiIdMap) {
		this.loseMondaiIdSet = loseMondaiIdMap;
	}

	public Set<String> addLoseMondaiIdSet(String mondaiId) {
		Set<String> set = getLoseMondaiIdSet();
		if (set.contains(mondaiId) == false) {
			set.add(mondaiId);
			setLoseMondaiIdSet(set);
		}
		return set;
	}

	public Set<String> removeLoseMondaiIdSet(String mondaiId) {
		Set<String> set = getLoseMondaiIdSet();
		if (set.contains(mondaiId) == true) {
			set.remove(mondaiId);
			setLoseMondaiIdSet(set);
		}
		return set;
	}

	public Set<String> getPendingMondaiIdSet() {
		return pendingMondaiIdSet;
	}

	public void setPendingMondaiIdSet(Set<String> pendingMondaiIdMap) {
		this.pendingMondaiIdSet = pendingMondaiIdMap;
	}

	public Set<String> addPendingMondaiIdSet(String mondaiId) {
		Set<String> set = getPendingMondaiIdSet();
		if (set.contains(mondaiId) == false) {
			set.add(mondaiId);
			setPendingMondaiIdSet(set);
		}
		return set;
	}

	public Set<String> removePendingMondaiIdSet(String mondaiId) {
		Set<String> set = getPendingMondaiIdSet();
		if (set.contains(mondaiId) == true) {
			set.remove(mondaiId);
			setPendingMondaiIdSet(set);
		}
		return set;
	}

	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);

	}

}
