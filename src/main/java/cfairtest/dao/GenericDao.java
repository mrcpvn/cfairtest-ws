package cfairtest.dao;

import java.lang.reflect.ParameterizedType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericDao {

	protected Class entityClass;
	
	@PersistenceContext
	private EntityManager em;

	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
	}
	
	// Stores a new message:
	public <T> void persist(T entity) {
		em.persist(entity);
	}
	
	//Find an element
	public <T,U> U findById(T id){
		return (U) em.find(entityClass, id);
	}
}
