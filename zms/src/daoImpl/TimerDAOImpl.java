package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.Page;
import dao.TimerDao;
import entity.Timer;

public class TimerDAOImpl extends HibernateDaoSupport implements TimerDao {

	public Timer queryTimer(String timername) {
		List list=this.getHibernateTemplate().find("select timer from Timer timer where timer.timername=?",timername);
		if(list.size()<=0){
			return null;
		}
		else {
			return (Timer)list.get(0);
		}
	}
	
	
	public Timer queryTimerById(int id){
		List list=this.getHibernateTemplate().find("select timer from Timer timer where timer.id=?",id);
		if(list.size() == 0) {
			return null;
		} else {
			return (Timer)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Timer> queryAllTimerByPage(final Page page,final Timer timer){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				 StringBuffer sql = new StringBuffer("select timer from Timer timer where 1=1");
				if(timer!=null){
					if(timer.getName()!=null&&!timer.getName().equals("")){
						sql.append("and name like '%"+timer.getName()+"%'");
					}
				}
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(Timer timer){
		this.getHibernateTemplate().saveOrUpdate(timer);
	}
	
	public void update(Timer timer){
		this.getHibernateTemplate().update(timer);
	}
	
	public void delete(Timer timer){
		Timer s = (Timer)getHibernateTemplate().
				load(Timer.class, timer.getId());//加载对象
		this.getHibernateTemplate().delete(s);
	}

	public List<Timer> queryAllTimer() {
		List list=this.getHibernateTemplate().find("select timer from Timer timer");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from Timer timer");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(Timer timer) {
	    StringBuffer sql = new StringBuffer("select count(*) from Timer timer where 1=1");
	    if(timer!=null){
			if(timer.getName()!=null&&!timer.getName().equals("")){
				sql.append("and name like '%"+timer.getName()+"%'");
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}




}
