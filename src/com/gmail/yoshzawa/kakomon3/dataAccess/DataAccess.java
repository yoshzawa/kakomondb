package com.gmail.yoshzawa.kakomon3.dataAccess;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class DataAccess {

	/**
	 * LowLevelApiのdatastoreServiceを取得する
	 * 
	 * @return datastoreService
	 */
	private static final DatastoreService getDatastoreService() {
		DatastoreService datastoreService = DatastoreServiceFactory
				.getDatastoreService();
		return datastoreService;
	}

	/**
	 * LowLevelApiでEntityを保存
	 * 
	 * @param entity
	 *            保存するEntity
	 */
	protected final void putEntity(Entity entity) {
		DatastoreService datastoreService = getDatastoreService();
		datastoreService.put(entity);
	}

	/**
	 * putEntityのAlias
	 * 
	 * @param entity
	 *            保存するEntity
	 */
	protected final void makePersistent(Entity entity) {
		putEntity(entity);
	}

	/**
	 * 一覧を取得
	 * 
	 * @param kind
	 *            カインド名
	 * @return 取得した結果
	 */
	protected static final Iterable<Entity> getResults(String kind) {
		Query query = new Query(kind);

		DatastoreService datastoreService = getDatastoreService();
		PreparedQuery pQuery = datastoreService.prepare(query);
		Iterable<Entity> results = pQuery.asIterable();
		return results;
	}

	/**
	 * エンティティを生成
	 * 
	 * @param kind
	 *            カインド名
	 * @param name
	 *            キー値
	 * @return 生成したエンティティ
	 */
	protected final Entity makeEntity(String kind, String name) {
		Key key = KeyFactory.createKey(kind, name);
		Entity entity = new Entity(key);
		return entity;
	}

	/**
	 * キーを指定してエンティティを取得
	 * 
	 * @param name
	 *            キー値
	 * @param kind
	 *            カインド名
	 * @return 取得したエンティティ
	 */
	protected static final Entity getObjectById(String name, String kind) {
		Entity entity = null;
		try {
			Key key = KeyFactory.createKey(kind, name);

			DatastoreService datastoreService = getDatastoreService();
			entity = datastoreService.get(key);
		} catch (EntityNotFoundException e) {
		}
		return entity;
	}
}
