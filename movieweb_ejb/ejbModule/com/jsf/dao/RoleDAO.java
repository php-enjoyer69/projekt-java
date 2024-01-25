package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Role;
import com.jsf.entities.Movie;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class RoleDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Role role) {
		em.persist(role);
	}

	public Role merge(Role role) {
		return em.merge(role);
	}

	public void remove(Role role) {
		em.remove(em.merge(role));
	}

	public Role find(Object id) {
		return em.find(Role.class, id);
	}

	public List<Role> getFullList() {
		List<Role> list = null;

		Query query = em.createQuery("select r from Role r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Role> getListForMovie(int idMovie) {
		List<Role> list = null;

		Query query = em.createQuery("select r from Role r where r.id.idmovie = " + idMovie);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			System.out.println(list);
			e.printStackTrace();
			
		}

		return list;
	}
	
	public List<Role> getListForPerson(int idPerson) {
		List<Role> list = null;

		Query query = em.createQuery("select r from Role r where r.id.idperson = " + idPerson);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			System.out.println(list);
			e.printStackTrace();
			
		}

		return list;
	}

	public List<Role> getList(Map<String, Object> searchParams) {
		List<Role> list = null;

		// 1. Build query string with parameters
		String select = "select r ";
		String from = "from Role r ";
		String where = "";
		String orderby = "order by r.person.surname";

		// search for surname
		String title = (String) searchParams.get("isDirector");
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "r.isDirector like :isDirector ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (title != null) {
			query.setParameter("isDirector", title+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

}
