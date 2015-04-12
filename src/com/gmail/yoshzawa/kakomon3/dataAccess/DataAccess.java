package com.gmail.yoshzawa.kakomon3.dataAccess;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DataAccess {
	protected static DatastoreService getDatastoreService() {
		DatastoreService datastoreService = DatastoreServiceFactory
				.getDatastoreService();
		return datastoreService;
	}
}
