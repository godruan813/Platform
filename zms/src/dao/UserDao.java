package dao;

import java.util.List;

import utils.Page;
import entity.User;
import entity.User;

public interface UserDao {
	
	String loginQuery(String username);

	User queryUser(String username);
	
	User queryUserById(int id);
	List<User> queryAllUser();
	void add(User user);
	void update(User user);
	void delete(User user);
	List<User> queryAllUserByPage(final Page page,final User user);
	int queryAllCount();
	int queryAllCount(User User);

}
