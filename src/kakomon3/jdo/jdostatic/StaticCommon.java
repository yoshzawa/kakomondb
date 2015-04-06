package kakomon3.jdo.jdostatic;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class StaticCommon {
	@SuppressWarnings("unchecked")
	protected static <T> List<T> getList(PersistenceManager pm, Class<T> i) {
		Query query = pm.newQuery(i);
		List<T> list = (List<T>) query.execute();
		return list;
	}
}
