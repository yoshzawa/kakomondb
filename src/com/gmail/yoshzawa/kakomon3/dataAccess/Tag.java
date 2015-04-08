package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import kakomon3.jdo.Mondai;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable
public class Tag extends TagStatic{

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

		List<String> list = getMondais();
		list.add(mondaiURL);
		setMondais(list);

		mondai.addTags(getName());
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
		this(e.getKey().getName(),(List<String>) e.getProperty("mondais"));
	}

	public final Tag makePersistent() {

		// TODO
		return null;
	}

	public static Tag GetObjectById(String name) {
		try {
			DatastoreService datastoreService = DatastoreServiceFactory
					.getDatastoreService();
			Key key = KeyFactory.createKey("Tag", name);
			Entity entity = datastoreService.get(key);
			Tag tag = new Tag(entity);
			return tag;
		} catch (EntityNotFoundException e) {
			return null;
		}
	}



}
