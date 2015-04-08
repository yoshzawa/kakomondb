package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import kakomon3.jdo.Mondai;
import kakomon3.jdo.Tag;

public class TagStatic  {
	
	public static List<Tag> getList(PersistenceManager pm) {

		return getList(pm, Tag.class);

	}

	public static Map<String, Tag> getMap(PersistenceManager pm) {
		List<Tag> list = getList(pm);
		return getMap(pm, list);
	}

	public static Map<String, Tag> getMap(PersistenceManager pm,
			List<Tag> tagList) {

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : tagList) {
			map.put(g.getName(), g);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> getList(PersistenceManager pm, Class<T> i) {
		Query query = pm.newQuery(i);
		List<T> list = (List<T>) query.execute();
		return list;
	}
	

}
