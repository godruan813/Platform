package daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.ARRAYLENGTH;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.Page;
import dao.PTaskDAO;
import entity.Computer;
import entity.PTask;

public class PTaskDAOImpl extends HibernateDaoSupport implements PTaskDAO{
	
	public PTask queryPTaskById(long id){
		List list=this.getHibernateTemplate().find("select pTask from PTask pTask where pTask.id='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (PTask)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PTask> queryAllPTaskByPage(final Page page,final PTask pTask){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer("select pTask from PTask pTask where 1=1");
				   if(pTask!=null){
						if(pTask.getCreater()!=null&&!pTask.getCreater().equals("")){
							sql.append("and detail like '%"+pTask.getCreater()+"%'");
						}
				   }
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(PTask pTask){
		this.getHibernateTemplate().saveOrUpdate(pTask);
	}
	
	public void update(PTask pTask){
		this.getHibernateTemplate().update(pTask);
	}
	
	public void delete(PTask pTask){
		this.getHibernateTemplate().delete(pTask);
	}

	public List<PTask> queryAllPTask() {
		List list=this.getHibernateTemplate().find("select pTask from PTask pTask");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from PTask pTask");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(PTask pTask) {
	    StringBuffer sql = new StringBuffer("select count(*) from PTask pTask where 1=1");
	    if(pTask!=null){
			if(pTask.getCreater()!=null&&!pTask.getCreater().equals("")){
				sql.append("and detail like '%"+pTask.getCreater()+"%'");
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}
	
	public PTask queryPTaskByIds(long id){
		List list=this.getHibernateTemplate().find("from PTask pTask where pTask.id='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (PTask)list.get(0);
		}
	}

	public Computer queryPTaskBycom(long id){
		List list=this.getHibernateTemplate().find("select computer from Computer computer where computer.id='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (Computer)list.get(0);
		}
	}

	public List<PTask> getPTaskOfhours(Date from, Date to, String user,String pids) {
		List<Long> p=new ArrayList<Long>();
		if(!StringUtils.isEmpty(pids)){
			for(int i=0;i<pids.split(",").length;i++){
				p.add(Long.parseLong((pids.split(",")[i])));
			}
		}
		Session session=super.getSession(true);
		Query query = session.createQuery("select pTask from PTask pTask where pTask.creater=? AND pTask.opertime BETWEEN ? AND ? AND pTask.id in (:p)");//
		query.setString(0, user);
		query.setTimestamp(1, from);
		query.setTimestamp(2, to);
		query.setParameterList("p", p);
		List list=query.list();
		releaseSession(session);
		return list;
	}


}
