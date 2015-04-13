package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public class TagStatic extends DataAccess {

	public static final Map<String, Tag> getMap() {
		List<Tag> list = getList();
		return getMap(list);
	}

	public static final Map<String, Tag> getMap(List<Tag> tagList) {

		Map<String, Tag> map = new HashMap<String, Tag>();

		for (Tag g : tagList) {
			map.put(g.getName(), g);
		}
		return map;
	}

	public static final List<Tag> getList() {

		String kind = "Tag";

		Iterable<Entity> result = getResults(kind);

		List<Tag> tagList = new ArrayList<>();
		for (Entity e : result) {
			Tag tag = new Tag(e);
			tagList.add(tag);
		}
		return tagList;
	}

	public static final Tag GetObjectById(String name) {
		String kind = "Tag";
		Tag tag = null;

		Entity entity = getObjectById(name, kind);
		if (entity != null) {
			tag = new Tag(entity);
		}
		return tag;
	}

}
