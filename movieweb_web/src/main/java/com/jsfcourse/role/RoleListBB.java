package com.jsfcourse.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RoleDAO;
import com.jsf.entities.Movie;
import com.jsf.entities.Person;
import com.jsf.entities.Role;

@Named
@RequestScoped
public class RoleListBB {
	private static final String PAGE_ROLE_EDIT = "/pages/edit/roleEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Byte isDirector;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RoleDAO roleDAO;
	
	@ManyToOne
	@JoinColumn(name="idmovie")
	private Movie movie;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="idperson")
	private Person person;

		
	public Byte getIsDirector() {
		return isDirector;
	}

	public void setIsDirector(Byte isDirector) {
		this.isDirector = isDirector;
	}

	public List<Role> getFullList(){
		return roleDAO.getFullList();
	}


	public List<Role> getList(){
		List<Role> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (isDirector != null){
			searchParams.put("isDirector", isDirector);
		}
		
		//2. Get list
		list = roleDAO.getList(searchParams);
		
		return list;
	}

	public String newRole(){
		Role role = new Role();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("role", role);
		
		return PAGE_ROLE_EDIT;
	}

	public String editRole(Role role){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("role", role);
		
		return PAGE_ROLE_EDIT;
	}

	public String deleteRole(Role role){
		roleDAO.remove(role);
		return PAGE_STAY_AT_THE_SAME;
	}
}
