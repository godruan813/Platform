package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ModuleDAO;

import entity.Module;
import service.ModuleService;

public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleDAO moduleDAO;
	
	public List<Module> getAllModules() {
		return moduleDAO.queryAllModules();
	}

	public Module getModule(int id) {
		return moduleDAO.queryModule(id);
	}

	public void update(Module module) {
		 moduleDAO.update(module);
	}

	public void delete(Module module) {
		 moduleDAO.delete(module);
	}
	
	public void deleteAll(List<Module> modules) {
		 moduleDAO.deleteAll(modules);
	}

	
	public List<Module> getChild(Module module) {
		return moduleDAO.queryChildModule(module.getId());
	}


}
