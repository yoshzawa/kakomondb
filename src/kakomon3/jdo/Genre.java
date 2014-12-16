package kakomon3.jdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Genre {

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String name;

	@Persistent
	private List<String> mondais;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMondais() {
		return mondais;
	}

	public void setMondais(List<String> mondais) {
		this.mondais = mondais;
	}

	public void addMondais(String mondaiId) {
		List<String> list = getMondais();
		list.add(mondaiId);
		setMondais(list);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondais(new ArrayList<String>());
	}
	
	/**
	 * Genre�S����List<Genre>�Ŏ擾����
	 * @param pm PersistenceManager�̃C���X�^���X
	 * @return �S������List
	 */
	public static List<Genre> findAll(PersistenceManager pm){
		Query query = pm.newQuery(Genre.class);
		@SuppressWarnings("unchecked")
		List<Genre> list = (List<Genre>) query.execute();
		return list;
	}

	/**
	 * Genre�S����id��name��Map<String id,String name>���擾����
	 * @param pm PersistenceManager�̃C���X�^���X
	 * @return Map
	 */
	public static Map<String,String> getMapAll(PersistenceManager pm){
		List<Genre> list2 = Genre.findAll(pm);

		Map<String, String> mapTag = new HashMap<String, String>();

		for (Genre g : list2) {
			mapTag.put(g.getId(), g.getName());
		}
		return mapTag;
	}
	
	

	public static void init(PersistenceManager pm) {
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"1-01", "��b���_"});
		list.add(new String[]{"1-02", "�A���S���Y���ƃv���O���~���O"});
		list.add(new String[]{"2-03", "�R���s���[�^�\���v�f"});

		for(String[] s:list){
			Genre g=new Genre(s[0], s[1]); 
			pm.makePersistent(g);
		}
	}
}
