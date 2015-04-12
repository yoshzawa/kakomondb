package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import kakomon3.jdo.Mondai;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Tag extends TagStatic {

	private String name;
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

		addMondais(mondaiURL);

		mondai.addTags(getName());
	}

	public final void addMondais(String mondaiId) {
		List<String> list = getMondais();
		list.add(mondaiId);
		setMondais(list);
	}

	public Tag(String name) {
		setName(name);
		setMondais(new ArrayList<String>());
	}

	public Tag(String name, List<String> mondais) {
		setName(name);
		setMondais(mondais);
	}

	@SuppressWarnings("unchecked")
	public Tag(Entity e) {
		this(e.getKey().getName(), (List<String>) e.getProperty("mondais"));
	}

	public final Tag makePersistent() {
		DatastoreService datastoreService = getDatastoreService();

		Key key = KeyFactory.createKey("Tag", getName());
		Entity entity = new Entity(key);
		entity.setProperty("mondais", getMondais());

		datastoreService.put(entity);

		return this;
	}

}
