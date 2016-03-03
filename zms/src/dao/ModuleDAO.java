package dao;

import java.util.List;

import entity.Module;

public interface ModuleDAO {
	
	List<Module> queryAllModules();

	Module queryModule(int id);
	
	void update(Module module);
	
	void delete(Module module);

	List<Module> queryChildModule(int id);

	void deleteAll(List<Module> moduels);
}
