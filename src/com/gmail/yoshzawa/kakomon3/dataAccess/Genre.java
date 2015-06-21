package com.gmail.yoshzawa.kakomon3.dataAccess;

import java.util.HashSet;
import java.util.Set;

public class Genre {
	private String id;

	private String name;
	private Set<String> mondaiList;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Set<String> getMondais() {
		return mondaiList;
	}

	public final void setMondais(Set<String> mondais) {
		this.mondaiList = mondais;
	}

	public final void addMondais(String mondaiId) {
		Set<String> set = getMondais();
		set.add(mondaiId);
		setMondais(set);
	}

	public Genre(String id, String name) {
		setId(id);
		setName(name);
		setMondais(new HashSet<String>());
	}
}
