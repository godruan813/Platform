package daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ModuleDAO;
import entity.Module;

public class ModuleDAOImpl extends HibernateDaoSupport implements ModuleDAO {

	public List<Module> queryAllModules() {
		List list =getHibernateTemplate().find("select module from Module module");
		return list;
	}

	public Module queryModule(int id) {
		List list =getHibernateTemplate().find("select module from Module module where module.id=?",id);
		if(list.size()<=0){
			return null;
		}
		return (Module)list.get(0);
	}

	public void update(Module module) {
		this.getHibernateTemplate().saveOrUpdate(module);
	}

	public void delete(Module module) {
		this.getHibernateTemplate().delete(module);
	}

	public List<Module> queryChildModule(int id) {
		
		return this.getHibernateTemplate().find("select module from Module module where module.parent=?",id);
	}

	public void deleteAll(List<Module> modules) {
		this.getHibernateTemplate().deleteAll(modules);
		
	}

}
