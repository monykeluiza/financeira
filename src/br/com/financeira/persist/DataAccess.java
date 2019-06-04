package br.com.financeira.persist;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class DataAccess<T> {

	private final static String UNIT_NAME = "financeira";
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	public DataAccess() {
	}

	private Class<T> type;

	public DataAccess(Class<T> type) {
		this.type = type;
	}

	public T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	public T update(T t) {
		this.em.merge(t);
		this.em.flush();
		return t;
	}

	public T find(Object id) {
		return this.em.find(this.type, id);
	}

	public void delete(Object id) {
		Object ref = this.em.getReference(this.type, id);
		this.em.remove(ref);
	}

	public void remove(T t) {
		this.em.remove(this.em.merge(t));
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName) {
		return this.em.createNamedQuery(namedQueryName).getResultList();
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName, Map parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql) {
		return this.em.createNativeQuery(sql, type).getResultList();
	}
	
	public int countTotalRecord(String namedQueryName) {
		Query query = em.createNamedQuery(namedQueryName);
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findWithNamedQuery(String namedQueryName, Map parameters,int resultLimit) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int findCountWithNamedQuery(String namedQueryName, Map parameters) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findWithNamedQueryUniqueOrNull(String namedQueryName, Map parameters) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);

	}
	
	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName, int start, int end) {
		Query query = this.em.createNamedQuery(namedQueryName);
		query.setMaxResults(end + start);
		query.setFirstResult(start);
		return query.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findWithNamedQueryLimt(String namedQueryName,Map parameters, int start, int end) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		query.setMaxResults(end + start);
		query.setFirstResult(start);
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

}