package service;

import java.util.List;

import entity.Module;

public interface ModuleService {
	
	List<Module> getAllModules();

	Module getModule(int id);
	
	void update(Module module);
	
	void delete(Module module);
	
	void deleteAll(List<Module> modules);

	List<Module> getChild(Module module);
}
