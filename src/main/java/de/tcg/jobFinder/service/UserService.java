package de.tcg.jobFinder.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.User;

public interface UserService {

	public List<User> getUsers();

	public User getUserById(String userId);

	public boolean updateUserById(HttpServletRequest request, User user, String image);

	public Account createUser(HttpServletRequest request, User user, String image);

	public boolean deleteUser(HttpServletRequest request, String userId);

	public String getEmailUserById(String userId);

}
