package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.Page;
import dao.STaskDAO;
import dao.STaskDAO;
import entity.PTask;
import entity.STask;
import entity.Student;

public class STaskDAOImpl extends HibernateDaoSupport implements STaskDAO{
	
	public STask querySTaskById(long id){
		List list=this.getHibernateTemplate().find("select sTask from STask sTask where sTask.id='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (STask)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<STask> queryAllSTaskByPage(final Page page,final STask sTask){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer("select sTask from STask sTask where 1=1");
				   if(sTask!=null){
						if(sTask.getCreater()!=null&&!sTask.getCreater().equals("")){
							sql.append("and detail like '%"+sTask.getCreater()+"%'");
						}
				   }
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(STask sTask){
		this.getHibernateTemplate().saveOrUpdate(sTask);
	}
	
	public void update(STask sTask){
		this.getHibernateTemplate().update(sTask);
	}
	
	public void delete(STask sTask){
		this.getHibernateTemplate().delete(sTask);
	}

	public List<STask> queryAllSTask() {
		List list=this.getHibernateTemplate().find("select sTask from STask sTask");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from STask sTask");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(STask sTask) {
	    StringBuffer sql = new StringBuffer("select count(*) from STask sTask where 1=1");
	    if(sTask!=null){
			if(sTask.getCreater()!=null&&!sTask.getCreater().equals("")){
				sql.append("and detail like '%"+sTask.getCreater()+"%'");
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}

	public List<PTask> queryPTask(long id) {
		List list=this.getHibernateTemplate().find("select pTask from PTask pTask where pTask.id='"+id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<STask> queryAllSTaskByPId(long pId) {
		List list=this.getHibernateTemplate().find("select sTask from STask sTask where sTask.pid='"+ pId+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}


}
