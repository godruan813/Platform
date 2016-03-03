package daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.FavoDAO;
import entity.Favo;

public class FavoDAOImpl extends HibernateDaoSupport implements FavoDAO {

	public void update(Favo favo) {
		this.getHibernateTemplate().saveOrUpdate(favo);
	}

	public Favo queryFavosByUsername(String username) {
		List list=this.getHibernateTemplate().find("from Favo favo where favo.username=?",username);
		if(list.size()<=0){
			return null;
		}
		return (Favo)list.get(0);
	}

}
