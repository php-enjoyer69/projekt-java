package com.jsfcourse.movie;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@ViewScoped
public class MovieEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MOVIE_LIST = "movieList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Movie movie = new Movie();
	private Movie loaded = null;

	@Inject
	FacesContext context;

	@EJB
	MovieDAO movieDAO;

	public Movie getMovie() {
		return movie;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && movie.getId_movie() != null) {
				loaded = movieDAO.find(movie.getId_movie());
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

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (movie.getId_movie() == null) {
				// new record
				movieDAO.create(movie);
			} else {
				// existing record
				movieDAO.merge(movie);
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
