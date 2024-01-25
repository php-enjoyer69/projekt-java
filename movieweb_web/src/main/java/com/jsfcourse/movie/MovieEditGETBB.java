package com.jsfcourse.movie;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.MovieDAO;
import com.jsf.dao.RoleDAO;
import com.jsf.dao.PersonDAO;
import com.jsf.entities.Movie;
import com.jsf.entities.Role;
import com.jsf.entities.Person;

@Named
@ViewScoped
public class MovieEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MOVIE_LIST = "movieList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Movie movie = new Movie();
	private Movie loaded = null;
	private List<Role> roles = null;

	@Inject
	FacesContext context;

	@EJB
	MovieDAO movieDAO;
	
	@EJB
	RoleDAO roleDAO;
	
	@EJB
	PersonDAO personDAO;

	public Movie getMovie() {
		return movie;
	}
	
	public List<Role> getRoles() {
		roles = roleDAO.getListForMovie(movie.getIdmovie());
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && movie.getIdmovie() != null) {
				loaded = movieDAO.find(movie.getIdmovie());
			}
			if (loaded != null) {
				movie = loaded;
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
		roles = roleDAO.getListForMovie(movie.getIdmovie());
		return roles;
	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (movie.getIdmovie() == null) {
				// new record
				movieDAO.create(movie);
			} else {
				// existing record
				for (Role role : roles ) {
				roleDAO.merge(role);
				}
				movieDAO.merge(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_STAY_AT_THE_SAME;
	}
	
	@Named
	@ViewScoped
	public class MovieManagedBean {

	    @ManagedProperty("#{movieDAO}")
	    private MovieDAO movieDAO;

	    private Movie updatedMovie = new Movie();

	    public void countRating() {
	        Movie resultMovie = movieDAO.countRating(updatedMovie);
	        // Dodaj logikę obsługi rezultatu, np. wyświetl komunikat o sukcesie
	    }

}
}
