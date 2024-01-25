package com.jsfcourse.person;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.PersonDAO;
import com.jsf.dao.RoleDAO;
import com.jsf.entities.Person;
import com.jsf.entities.Role;

@Named
@ViewScoped
public class PersonEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Person person = new Person();
	private Person loaded = null;
	private List<Role> roles = null;

	@Inject
	FacesContext context;

	@EJB
	PersonDAO personDAO;
	
	@EJB
	RoleDAO roleDAO;

	public Person getPerson() {
		return person;
	}
	
	public List<Role> getRoles() {
		roles = roleDAO.getListForPerson(person.getIdperson());
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && person.getIdperson() != null) {
				loaded = personDAO.find(person.getIdperson());
			}
			if (loaded != null) {
				person = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}
	
	public List<Role> getRoleList() {
		roles = roleDAO.getListForPerson(person.getIdperson());
		return roles;
	}
	

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (person.getIdperson() == null) {
				// new record
				personDAO.create(person);
			} else {
				// existing record
				for (Role role : roles ) {
				roleDAO.merge(role);
				}
				personDAO.merge(person);
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
