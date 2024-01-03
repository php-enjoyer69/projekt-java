package com.jsfcourse.db;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import com.jsfcourse.entities.User;

@Named
@RequestScoped
public class UserDAO {

	// simulate finding user in DB
	public User getUserFromDatabase(String login, String pass) {
		
		User u = null;

		if (login.equals("user") && pass.equals("user")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		if (login.equals("manager") && pass.equals("manager")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		if (login.equals("admin") && pass.equals("admin")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getLogin().equals("user")) {
			roles.add("user");
		}
		if (user.getLogin().equals("manager")) {
			roles.add("manager");
		}
		if (user.getLogin().equals("admin")) {
			roles.add("admin");
		}
		
		return roles;
	}
}
