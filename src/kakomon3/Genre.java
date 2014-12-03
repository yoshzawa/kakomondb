package kakomon3;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
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

	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the mondais
	 */
	public List<String> getMondais() {
		return mondais;
	}

	/**
	 * @param mondais the mondais to set
	 */
	public void setMondais(List<String> mondais) {
		this.mondais = mondais;
	}

	public Genre( String id , String name ) {
		setId(id);
		setName(name);
		setMondais(new ArrayList<String>());
	}
	
	public static void init(PersistenceManager pm){
		pm.makePersistent(new Genre("1-01","基礎理論"));
		pm.makePersistent(new Genre("1-02","アルゴリズムとプログラミング"));
		pm.makePersistent(new Genre("2-03","コンピュータ構成要素"));
	}
	
	
	
	
}
