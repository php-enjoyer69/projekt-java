package com.jsfcourse.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@RequestScoped
public class MovieListBB {
	private static final String PAGE_MOVIE_EDIT = "/pages/edit/movieEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	MovieDAO movieDAO;
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Movie> getFullList(){
		return movieDAO.getFullList();
	}

	public List<Movie> getList(){
		List<Movie> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		//2. Get list
		list = movieDAO.getList(searchParams);
		
		return list;
	}

	public String newMovie(){
		Movie movie = new Movie();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("movie", movie);
		
		return PAGE_MOVIE_EDIT;
	}

	public String editMovie(Movie movie){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("movie", movie);
		
		return PAGE_MOVIE_EDIT;
	}

	public String deleteMovie(Movie movie){
		movieDAO.remove(movie);
		return PAGE_STAY_AT_THE_SAME;
	}
}
