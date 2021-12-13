package de.tcg.jobFinder.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.tcg.jobFinder.entity.User;

public interface UserService {

	public List<User> getUsers(HttpServletRequest request);

	public User getUserById(HttpServletRequest request, String userId);

	public boolean updateUserById(HttpServletRequest request, User user);

}
