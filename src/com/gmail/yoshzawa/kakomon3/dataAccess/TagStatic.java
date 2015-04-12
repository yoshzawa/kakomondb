package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TagStatic extends DataAccess {

	public static Map<String, Tag> getMap() {
		List<Tag> list = getList();
		return getMap(list);
	}

	public static Map<String, Tag> getMap(List<Tag> tagList) {

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : tagList) {
			map.put(g.getName(), g);
		}
		return map;
	}

	public static List<Tag> getList() {

		DatastoreService datastoreService = getDatastoreService();
		Query query = new Query("Tag");
		PreparedQuery pQuery = datastoreService.prepare(query);

		List<Tag> tagList = new ArrayList<>();
		for (Entity e : pQuery.asIterable()) {
			Tag tag = new Tag(e);
			tagList.add(tag);
		}
		return tagList;
	}

	public static Tag GetObjectById(String name) {
		String kind = "Tag";
		try {
			DatastoreService datastoreService = getDatastoreService();

			Key key = KeyFactory.createKey(kind, name);
			Entity entity = datastoreService.get(key);
			Tag tag = new Tag(entity);
			return tag;
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
