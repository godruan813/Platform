package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.Page;

import com.sun.org.apache.bcel.internal.generic.Select;

import dao.UserDao;
import entity.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDao {

	public String loginQuery(String username) {
		Session session = this.getSession();
		Query query = session.createQuery("select password from User user where user.username=?");
		query.setString(0, username);
		Object obj=query.uniqueResult();
		return String.valueOf(obj);
	}

	public User queryUser(String username) {
		List list=this.getHibernateTemplate().find("select user from User user where user.username=?",username);
		if(list.size()<=0){
			return null;
		}
		else {
			return (User)list.get(0);
		}
	}
	
	public User queryUserById(int id){
		List list=this.getHibernateTemplate().find("select user from User user where user.id=?",id);
		if(list.size() == 0) {
			return null;
		} else {
			return (User)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> queryAllUserByPage(final Page page,final User user){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				 StringBuffer sql = new StringBuffer("select user from User user where 1=1");
				if(user!=null){
					if(user.getUsername()!=null&&!user.getUsername().equals("")){
						sql.append("and username like '%"+user.getUsername()+"%'");
					}
				}
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(User user){
		this.getHibernateTemplate().saveOrUpdate(user);
	}
	
	public void update(User user){
		this.getHibernateTemplate().update(user);
	}
	
	public void delete(User user){
		User s = (User)getHibernateTemplate().
				load(User.class, user.getId());//加载对象
		this.getHibernateTemplate().delete(s);
	}

	public List<User> queryAllUser() {
		List list=this.getHibernateTemplate().find("select user from User user");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from User user");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(User user) {
	    StringBuffer sql = new StringBuffer("select count(*) from User user where 1=1");
	    if(user!=null){
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql.append("and username like '%"+user.getUsername()+"%'");
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}


}
