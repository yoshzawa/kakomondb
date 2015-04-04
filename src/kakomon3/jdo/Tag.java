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

	public void addMondais(Mondai mondai) {
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



	public Tag makePersistent(PersistenceManager pm) {
		Tag result = pm.makePersistent(this);
		return result;
	}

}
