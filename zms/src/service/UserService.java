package service;

import java.util.List;

import utils.Page;
import entity.User;
import entity.User;

public interface UserService {

	String login(User user);
	
	User queryUser(User user);
	
	void add(User user);
	void update(User user);
	void delete(User user);
	User getUserById(User user);
	List<User> getAllUser();
	List<User> getAllUserByPage(Page page,User user);
	int getUserCount();
	int getUserCount(User user);
}
