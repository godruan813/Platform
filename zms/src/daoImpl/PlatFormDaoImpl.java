package daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.PlatFormDao;
import entity.AutoStep;
import entity.Case;
import entity.Computer;
import entity.PTask;
import entity.STask;

public class PlatFormDaoImpl extends HibernateDaoSupport implements PlatFormDao {

	public int savePTask(PTask pTask) {
		this.getHibernateTemplate().save(pTask);
		return (int) pTask.getId();
	}

	public int saveSTask(STask sTask) {
		this.getHibernateTemplate().save(sTask);
		return (int) sTask.getId();
	}

	public Computer getcomputer(String ip) {
		List list=this.getHibernateTemplate().find("from Computer computer where computer.ip = ?",ip);
		if(list.size() == 0) {
			return null;
		} else {
			return (Computer)list.get(0);
		}
	}

//	public List<PTask> getPTasks() {
//		List list=this.getHibernateTemplate().find("from Computer computer where computer.ip = '"+ip+"'");
//		return null;
//	}
//
//	public List<STask> getSTasks() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public void updateComputerStatus(String ip,String status) {
		Session session = this.getSession();
		Query query = session.createQuery("update Computer computer set computer.computerStatus =? where computer.ip=?");
		query.setString(0, status);
		query.setString(1, ip);
		query.executeUpdate();
		
	}

	public void updateTaskStatus(String sid, String taskStatus) {
		Session session = this.getSession();
		Query query = session.createQuery("update STask sTask set sTask.status = ? where sTask.sid= ?");
		query.setString(0, taskStatus);
		query.setString(1, sid);
		query.executeUpdate();
		
	}

	public void updateTaskStopBy(String sid, String stopBy) {
		Session session = this.getSession();
		Query query = session.createQuery("update STask sTask set sTask.stopBy = ? where sTask.sid= ?");
		query.setString(0, stopBy);
		query.setString(1, sid);
		query.executeUpdate();
	}

	public List<Case> findcasesByIds(String[] ids) {
		StringBuffer sql=new StringBuffer("select caze from Case caze where caze.id in (");
		for(String id:ids){
			sql.append("'");
			sql.append(id);
			sql.append("'");
			sql.append(',');
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		List<Case> list=this.getHibernateTemplate().find(sql.toString());
		return list;
	}

	public List<Computer> findIpByStatus(String... status) {
		StringBuffer sql=new StringBuffer("select computer from Computer computer where computer.computerStatus in (");
		for(String s:status){
			sql.append("'");
			sql.append(s);
			sql.append("'");
			sql.append(',');
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(") ORDER BY ip ASC");
		List<Computer> list=this.getHibernateTemplate().find(sql.toString());
		return list;
	}


	public void saveStep(List<AutoStep> list)
			throws Exception {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	public void deleteStep(int caseId) {
		Session session = this.getSession();
		Query query = session.createQuery("delete from AutoStep autoStep where autoStep.caseId = ?");
		query.setInteger(0, caseId);
		query.executeUpdate();
	}

	public void updateMethodByCaseId(String methodSrc, int caseId) {
		Session session = this.getSession();
		Query query = session.createQuery("update Case caze set caze.methodSrc=? where caze.caseId=?");
		query.setString(0, methodSrc);
		query.setInteger(1, caseId);
		query.executeUpdate();
		
	}

	public String getMethodSrc(String caseId) {
		List list=this.getHibernateTemplate().find("from Case caze where caze.caseId=?",caseId);
		if(list.size() == 0) {
			return null;
		} else {
			return ((Case)list.get(0)).getMethodSrc();
		}
	}


}