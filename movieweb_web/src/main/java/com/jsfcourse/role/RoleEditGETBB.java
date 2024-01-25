package com.jsfcourse.role;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.jsf.dao.RoleDAO;
import com.jsf.entities.Movie;
import com.jsf.entities.Person;
import com.jsf.entities.Role;

@Named
@ViewScoped
public class RoleEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MOVIE_LIST = "movieList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Role role = new Role();
	private Role loaded = null;

	@Inject
	FacesContext context;

	@EJB
	RoleDAO roleDAO;
	
	@ManyToOne
	@JoinColumn(name="idmovie")
	private Movie movie = new Movie();

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="idperson")
	private Person person = new Person();

	public Role getRole() {
		return role;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public Person getPerson() {
		return person;
	}
	
	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && role.getId() != null) {
				loaded = roleDAO.find(role.getId());
			}
			if (loaded != null) {
				role = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (role.getId() == null) {
				// new record
				roleDAO.create(role);
			} else {
				// existing record
				roleDAO.merge(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_MOVIE_LIST;
	}
}
