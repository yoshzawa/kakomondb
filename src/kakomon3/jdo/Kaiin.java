package kakomon3.jdo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable
public class Kaiin {

	@PrimaryKey
	@Persistent
	private User user;

	@Persistent
	private Map<String, List<String>> winMondaiIdMap;

	@Persistent
	private Map<String, List<String>> loseMondaiIdMap;

	@Persistent
	private Map<String, List<String>> pendingMondaiIdMap;

	@Persistent
	private List<String> genreId;

	@Persistent
	private Date created;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, List<String>> getWinMondaiIdMap() {
		return winMondaiIdMap;
	}

	public void setWinMondaiIdMap(Map<String, List<String>> winMondaiIdMap) {
		this.winMondaiIdMap = winMondaiIdMap;
	}

	public Map<String, List<String>> getLoseMondaiIdMap() {
		return loseMondaiIdMap;
	}

	public void setLoseMondaiIdMap(Map<String, List<String>> loseMondaiIdMap) {
		this.loseMondaiIdMap = loseMondaiIdMap;
	}

	public Map<String, List<String>> getPendingMondaiIdMap() {
		return pendingMondaiIdMap;
	}

	public void setPendingMondaiIdMap(
			Map<String, List<String>> pendingMondaiIdMap) {
		this.pendingMondaiIdMap = pendingMondaiIdMap;
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
		setUser(user);
		setWinMondaiIdMap(new HashMap<String, List<String>>());
		setLoseMondaiIdMap(new HashMap<String, List<String>>());
		setPendingMondaiIdMap(new HashMap<String, List<String>>());
		setCreated(new java.util.Date());
	}

	public int[] addWinMondaiIdMap(String genreId, String mondaiId) {
		int i[] = new int[3];
		{
			Map<String, List<String>> winMondaiMap = getWinMondaiIdMap();
			List<String> mondaiList = winMondaiMap.get(genreId);
			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				winMondaiMap.put(genreId, mondaiList);
				setWinMondaiIdMap(winMondaiMap);
			}
			i[0] = countMap(winMondaiMap);
		}
		{
			Map<String, List<String>> loseMondaiMap = getLoseMondaiIdMap();
			List<String> mondaiList = loseMondaiMap.get(genreId);
			if (mondaiList.contains(mondaiId) == true) {
				mondaiList.remove(mondaiId);
				loseMondaiMap.put(genreId, mondaiList);
				setLoseMondaiIdMap(loseMondaiMap);
			}
			i[1] = countMap(loseMondaiMap);

		}
		{
			Map<String, List<String>> pendingMondaiMap = getPendingMondaiIdMap();
			List<String> mondaiList = pendingMondaiMap.get(genreId);
			if (mondaiList.contains(mondaiId) == true) {
				mondaiList.remove(mondaiId);
				pendingMondaiMap.put(genreId, mondaiList);
				setPendingMondaiIdMap(pendingMondaiMap);
			}
			i[2] = countMap(pendingMondaiMap);
		}
		return i;
	}

	public int[] addLoseMondaiIdMap(String genreId, String mondaiId) {
		int i[] = new int[3];
		i[0] = 0;
		i[1] = 0;
		i[2] = 0;
		{
			Map<String, List<String>> winMondaiMap = getWinMondaiIdMap();
			i[0] = countMap(winMondaiMap);
		}
		{
			Map<String, List<String>> loseMondaiMap = getLoseMondaiIdMap();
			List<String> mondaiList = loseMondaiMap.get(genreId);
			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				loseMondaiMap.put(genreId, mondaiList);
				setLoseMondaiIdMap(loseMondaiMap);
			}
			i[1] = countMap(loseMondaiMap);
		}
		{
			Map<String, List<String>> pendingMondaiMap = getPendingMondaiIdMap();
			i[2] = countMap(pendingMondaiMap);
		}
		return i;
	}

	public int[] addPendingMondaiIdMap(String genreId, String mondaiId) {
		int i[] = new int[3];
		{
			Map<String, List<String>> winMondaiMap = getWinMondaiIdMap();
			i[0] = countMap(winMondaiMap);
		}
		{
			Map<String, List<String>> loseMondaiMap = getLoseMondaiIdMap();
			i[1] = countMap(loseMondaiMap);
		}
		{
			Map<String, List<String>> pendingMondaiMap = getPendingMondaiIdMap();
			List<String> mondaiList = pendingMondaiMap.get(genreId);
			if (mondaiList.contains(mondaiId) == false) {
				mondaiList.add(mondaiId);
				pendingMondaiMap.put(genreId, mondaiList);
				setPendingMondaiIdMap(pendingMondaiMap);
			}
			i[2] = countMap(pendingMondaiMap);
		}
		return i;
	}

	private int countMap(Map<String, List<String>> map) {
		int i = 0;
		for (List<String> m : map.values()) {
			i += m.size();
		}
		return i;
	}
	public void makeMap(PersistenceManager pm){
		List<Kaitou> list = Kaitou.getListByUser(pm, getUser());
		Map<String, Mondai> mondaiMap = Mondai.getMap(pm);
		for(Kaitou kaitou : list){
			String mondaiId = kaitou.getMondaiId();
			Mondai mondai = mondaiMap.get(mondaiId);
			String genreId = mondai.getGenre();
			if(kaitou.isSeikai() == true){
				addWinMondaiIdMap(genreId, mondaiId);
			}else{
				addLoseMondaiIdMap(genreId, mondaiId);
			}
		}
	}
	public Kaitou getObjectById(PersistenceManager pm,User user){
		return (Kaitou) pm.getObjectById(user);
	}
}
