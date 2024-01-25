package com.jsfcourse.role;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.PersonDAO;
import com.jsf.entities.Person;
import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;
import com.jsf.dao.RoleDAO;
import com.jsf.entities.Role;

@Named
@ViewScoped
public class RoleEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Role role = new Role();
	private Role loaded = null;

	@EJB
	RoleDAO roleDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	
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
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Role) flash.get("role");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			role = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
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

		return PAGE_PERSON_LIST;
	}
}
