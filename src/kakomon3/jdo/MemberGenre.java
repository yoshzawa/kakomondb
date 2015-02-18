package kakomon3.jdo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	private Member kaiin;
	
	@Persistent
	private String genreId;

	@Persistent
	private  Set<String> winMondaiIdMap;

	@Persistent
	private Set<String> loseMondaiIdMap;

	@Persistent
	private Set<String> pendingMondaiIdMap;

	public MemberGenre(String genreId, Member kaiin) {
		setKaiin(kaiin);
		setGenreId(genreId);
		setWinMondaiIdMap(new HashSet<String>());
		setLoseMondaiIdMap(new HashSet<String>());
		setPendingMondaiIdMap(new HashSet<String>());
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Member getKaiin() {
		return kaiin;
	}

	public void setKaiin(Member kaiin) {
		this.kaiin = kaiin;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public Set<String> getWinMondaiIdMap() {
		return winMondaiIdMap;
	}

	public void setWinMondaiIdMap(Set<String> winMondaiIdMap) {
		this.winMondaiIdMap = winMondaiIdMap;
	}

	public Set<String> getLoseMondaiIdMap() {
		return loseMondaiIdMap;
	}

	public void setLoseMondaiIdMap(Set<String> loseMondaiIdMap) {
		this.loseMondaiIdMap = loseMondaiIdMap;
	}

	public Set<String> getPendingMondaiIdMap() {
		return pendingMondaiIdMap;
	}

	public void setPendingMondaiIdMap(Set<String> pendingMondaiIdMap) {
		this.pendingMondaiIdMap = pendingMondaiIdMap;
	}

	public void makePersistent(PersistenceManager pm) {
		pm.makePersistent(this);
		
	}

	
	
}
