package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import service.UserService;
import utils.Page;
import dao.UserDao;
import entity.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public String login(User user) {
		return userDao.loginQuery(user.getUsername());
	}

	public User queryUser(User user) {
		return userDao.queryUser(user.getUsername());
	}
	
	public void add(User user) {
		userDao.add(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}

	public List<User> getAllUser() {
		return userDao.queryAllUser();
	}
	
	public User getUserById(User user){
		return userDao.queryUserById(user.getId());
	}
	
	public int getUserCount(){
		return userDao.queryAllCount();
	}
	
	public int getUserCount(User user){
		return userDao.queryAllCount(user);
	}
	
	public List<User> getAllUserByPage(Page page,User user) {
		return userDao.queryAllUserByPage(page, user);
	}

}
