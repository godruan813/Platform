package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import utils.Page;

import dao.StudentDAO;

 
import entity.Student;

public class StudentDAOImpl extends HibernateDaoSupport implements StudentDAO{
	
	public Student queryStudentById(String id){
		List list=this.getHibernateTemplate().find("select student from Student student where student.sid='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (Student)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> queryAllStudentByPage(final Page page,final Student stu){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				 StringBuffer sql = new StringBuffer("select student from Student student where 1=1");
				if(stu!=null){
					if(stu.getSid()!=null&&!stu.getSid().equals("")){
						sql.append("and sid like '%"+stu.getSid()+"%'");
					}
				}
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(Student stu){
		this.getHibernateTemplate().saveOrUpdate(stu);
	}
	
	public void update(Student stu){
		this.getHibernateTemplate().update(stu);
	}
	
	public void delete(Student stu){
		Student s = (Student)getHibernateTemplate().
				load(Student.class, stu.getPk());//加载对象
		this.getHibernateTemplate().delete(s);
	}

	public List<Student> queryAllStudent() {
		List list=this.getHibernateTemplate().find("select student from Student student");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from Student student");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(Student stu) {
	    StringBuffer sql = new StringBuffer("select count(*) from Student student where 1=1");
	    if(stu!=null){
			if(stu.getSid()!=null&&!stu.getSid().equals("")){
				sql.append("and sid like '%"+stu.getSid()+"%'");
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}
	
}
