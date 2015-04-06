package kakomon3.jdo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import kakomon3.jdo.cache.GenreCache;
import kakomon3.jdo.jdostatic.GenreStatic;

@PersistenceCapable
public class Genre extends GenreStatic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697303811337883999L;

	@PrimaryKey
	@Persistent
	private String id;

	@Persistent
	private String name;

	@Persistent
	private Set<String> mondaiList;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Set<String> getMondais() {
		return mondaiList;
	}

	public final void setMondais(Set<String> mondais) {
		this.mondaiList = mondais;
	}

	public final void addMondais(String mondaiId) {
		Set<String> set = getMondais();
		set.add(mondaiId);
		setMondais(set);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondais(new HashSet<String>());
	}



	public final Genre makePersistent(PersistenceManager pm, boolean useCache) {
		Genre result;
		if (useCache == true) {
			result = GenreCache.makePersistent(pm, this);
		} else {
			result = pm.makePersistent(this);
		}
		return result;
	}

	public final Genre makePersistent(PersistenceManager pm) {
		return makePersistent(pm, useCache);
	}


}