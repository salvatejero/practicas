package com.practicas.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@PersistenceContext
	EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected T getByKey(PK key) {
		return (T) entityManager.find(persistentClass, key);
	}

	@Transactional
	protected void persist(T entity) {
		entityManager.persist(entity);
	}
	
	@Transactional
	protected void update(T entity) {
		entityManager.merge(entity);
	}
	
	@Transactional
	protected void delete(T entity) {
		entityManager.remove(entity);
	}

}
