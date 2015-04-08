package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TagStatic {

	public static Map<String, Tag> getMap() {
		List<Tag> list = getList();
		return getMap( list);
	}

	public static Map<String, Tag> getMap(
			List<Tag> tagList) {

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : tagList) {
			map.put(g.getName(), g);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static List<Tag> getList() {

		DatastoreService datastoreService = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Tag");
		PreparedQuery pQuery = datastoreService.prepare(query);

		List<Tag> tagList = new ArrayList<>();
		for (Entity e : pQuery.asIterable()) {
//			String name = (String) e.getProperty("Name");
			Key key = e.getKey();
			String name = key.getName();
			List<String> mondais = (List<String>) e.getProperty("mondais");
			Tag tag = new Tag(name, mondais);
			tagList.add(tag);
		}
		return tagList;
	}
}
