package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Movie;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class MovieDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Movie movie) {
		em.persist(movie);
	}

	public Movie merge(Movie movie) {
		return em.merge(movie);
	}

	public void remove(Movie movie) {
		em.remove(em.merge(movie));
	}

	public Movie find(Object id) {
		return em.find(Movie.class, id);
	}

	public List<Movie> getFullList() {
		List<Movie> list = null;

		Query query = em.createQuery("select m from Movie m");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Movie> getList(Map<String, Object> searchParams) {
		List<Movie> list = null;

		// 1. Build query string with parameters
		String select = "select m ";
		String from = "from Movie m ";
		String where = "";
		String orderby = "order by m.title";

		// search for surname
		String title = (String) searchParams.get("title");
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "m.title like :title ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (title != null) {
			query.setParameter("title", title+"%");
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
