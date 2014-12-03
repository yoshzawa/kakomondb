package kakomon3;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Mondai {
	
	@PrimaryKey
	@Persistent
	private String URL;
	
	@Persistent
	private String comment;

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public Mondai( String URL , String comment) {
		setURL(URL);
		setComment(comment);
	}
	
	public static void init(PersistenceManager pm){
		pm.makePersistent( new Mondai("ap/APH240401.png", "応用情報平成24年度春問01") );
		pm.makePersistent( new Mondai("ap/APH240402.png", "応用情報平成24年度春問02") );
		pm.makePersistent( new Mondai("ap/APH240403.png", "応用情報平成24年度春問03") );
		pm.makePersistent( new Mondai("ap/APH240404.png", "応用情報平成24年度春問04") );
		pm.makePersistent( new Mondai("ap/APH240405.png", "応用情報平成24年度春問05") );
		
		pm.makePersistent( new Mondai("ap/APH241001.png", "応用情報平成24年度秋問01") );
		pm.makePersistent( new Mondai("ap/APH241002.png", "応用情報平成24年度秋問02") );
		pm.makePersistent( new Mondai("ap/APH241003.png", "応用情報平成24年度秋問03") );
		pm.makePersistent( new Mondai("ap/APH241004.png", "応用情報平成24年度秋問04") );
		pm.makePersistent( new Mondai("ap/APH241005.png", "応用情報平成24年度秋問05") );

		pm.makePersistent( new Mondai("ap/APH250401.png", "応用情報平成25年度春問01") );
		pm.makePersistent( new Mondai("ap/APH250402.png", "応用情報平成25年度春問02") );
		pm.makePersistent( new Mondai("ap/APH250403.png", "応用情報平成25年度春問03") );
		pm.makePersistent( new Mondai("ap/APH250404.png", "応用情報平成25年度春問04") );
		
	}
	

}
