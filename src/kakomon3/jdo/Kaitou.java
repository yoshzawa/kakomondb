package kakomon3.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class Kaitou {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private User user;

	@Persistent
	private String mondaiId;
	
	@Persistent
	private Sentaku sentaku;
	
	

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMondaiId() {
		return mondaiId;
	}

	public void setMondaiId(String mondaiId) {
		this.mondaiId = mondaiId;
	}

	
	public Sentaku getSentaku() {
		return sentaku;
	}

	public void setSentaku(Sentaku sentaku) {
		this.sentaku = sentaku;
	}

	public Kaitou(User user, String mondaiId) {
		this(user, mondaiId, null);

	}
	public Kaitou(User user, String mondaiId , Sentaku sentaku) {
		setUser(user);
		setMondaiId(mondaiId);
		setSentaku(sentaku);
	}

}
