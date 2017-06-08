package service;

import dao.UserDao;

/**
 * @author Andory
 *
 */
public class UserService {

	private UserDao userDao;

	public UserService() {
		//init params in Constructor
		this.userDao = new UserDao();
	}
	
	public boolean checkUser(String username, String password) {
		return userDao.isExist(username, password);
	}
	
}
