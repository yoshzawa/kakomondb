package kakomon3.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Tag extends TagStatic{

	@PrimaryKey
	@Persistent
	private String name;

	@Persistent
	private List<String> mondais;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<String> getMondais() {
		return mondais;
	}

	public final void setMondais(List<String> mondais) {
		this.mondais = mondais;
	}

	public final void addMondais(Mondai mondai) {
		String mondaiURL = mondai.getId();

		List<String> list = getMondais();
		list.add(mondaiURL);
		setMondais(list);

		mondai.addTags(getName());
	}

	public Tag(String name) {
		setName(name);
		setMondais(new ArrayList<String>());
	}



	public final Tag makePersistent(PersistenceManager pm) {
		Tag result = pm.makePersistent(this);
		return result;
	}

}
