package cfairtest.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericDao<T> {

	protected Class<T> entityClass;

	@PersistenceContext
	private EntityManager em;
	
	public GenericDao() {
		super();
	}

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	// Stores a new message:
	public <T> void persist(T entity) {
		em.persist(entity);
	}

	// Find an element
	public <T, U> U findById(T id) {
		return (U) em.find(entityClass, id);
	}
}
