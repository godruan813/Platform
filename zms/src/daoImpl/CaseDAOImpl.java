package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import utils.Page;
import dao.CaseDAO;
import entity.AutoStep;
import entity.Case;
import entity.Casestep;
import entity.Module;
import entity.Student;
import freemarker.template.utility.StringUtil;

public class CaseDAOImpl extends HibernateDaoSupport implements CaseDAO{
	
	public Case queryCaseById(int id){
		List list=this.getHibernateTemplate().find("select cases from Case cases where cases.id='"+ id+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return (Case)list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Case> queryAllCaseByPage(final Page page,final Case cases){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer("select cases from Case cases where 1=1 ");
				   if(cases!=null){
						if(cases.getId()!=0){
							sql.append("and id = '"+cases.getId()+"'");
						}
						else if(!StringUtils.isEmpty(cases.getModuleId())){
							String module=cases.getModuleId();
							if(module.contains(",")){
								sql.append("and moduleId in (");
								for(String m:module.split("\\,")){
									sql.append("'"+m+"',");
								}
								sql=sql.deleteCharAt(sql.lastIndexOf(","));
								sql.append(")");
							}
							else {
								sql.append("and moduleId like '"+cases.getModuleId()+"'");
							}
						}
				   }
				sql.append(" order by id");
				Query query = session.createQuery(sql.toString());
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
		});

	}
	
	public void add(Case cases){
		this.getHibernateTemplate().saveOrUpdate(cases);
	}
	
	public void update(Case cases){
		this.getHibernateTemplate().update(cases);
	}
	
	public void delete(Case cases){
		this.getHibernateTemplate().delete(cases);
	}

	public List<Case> queryAllCase() {
		List list=this.getHibernateTemplate().find("select cases from Case cases");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public int queryAllCount() {
		List find = this.getHibernateTemplate().find("select count(*) from Case cases");
		return Integer.parseInt(find.get(0).toString());
	}

	public int queryAllCount(Case cases) {
	    StringBuffer sql = new StringBuffer("select count(*) from Case cases where 1=1");
	    if(cases!=null){
			if(cases.getDetail()!=null&&!cases.getDetail().equals("")){
				sql.append("and detail like '%"+cases.getDetail()+"%'");
			}
			if(cases.getModuleId()!=null&&!cases.getModuleId().equals("")){
				String module=cases.getModuleId();
				if(module.contains(",")){
					sql.append("and moduleId in (");
					for(String m:module.split("\\,")){
						sql.append("'"+m+"',");
					}
					sql=sql.deleteCharAt(sql.lastIndexOf(","));
					sql.append(")");
				}
				else {
					sql.append("and moduleId like '"+cases.getModuleId()+"'");
				}
			}
		}
		List find = this.getHibernateTemplate().find(sql.toString());
		return Integer.parseInt(find.get(0).toString());
	}

	public String queryModuleName(String ModuleId) {
		List list=this.getHibernateTemplate().find("select module from Module module where module.id='"+ ModuleId+"'");
		if(list.size() == 0) {
			return null;
		} else {
			Module module=(Module) list.get(0);
			return (String)module.getName();
		}
	}

	public Module queryParentModule(String moduleId) {
		List list=this.getHibernateTemplate().find("select module from Module module where module.id=(select parent from Module module1 where module1.id='"+moduleId+"')");
		if(list.size() == 0) {
			return null;
		} else {
			return (Module) list.get(0);
		}
	}
	
	public List queryChildModule(String parentModuleId) {
		List list=this.getHibernateTemplate().find("select module from Module module where module.parent='"+ parentModuleId+"'");
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Case> queryCaseByCaseId(List<String> caseIds) {
		StringBuffer sb=new StringBuffer("select cases from Case cases where cases.id in(");
		for(String caseId:caseIds){
			sb.append("'");
			sb.append(caseId);
			sb.append("',");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		List list=this.getHibernateTemplate().find(sb.toString());
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public void addStep(List<Casestep> casesteps) {
		this.getHibernateTemplate().saveOrUpdateAll(casesteps);
	}
	
	public String getMaxVersion(String caseId){
		Session session = super.getSession();
		Query query=session.createSQLQuery("select MAX(version) as max from tb_casestep where caseId=?").addScalar("max",Hibernate.STRING);
		query.setString(0, caseId);
		String res=(String) query.uniqueResult();
		releaseSession(session);
		return res;
	}
	
	public List<Casestep> getStep(int caseId,String version){
		
		List list=this.getHibernateTemplate().find("from Casestep casestep where casestep.caseId=? and casestep.version=?",new Object[]{caseId,version});
		return list;
	}
	
	

}
