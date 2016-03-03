package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import service.CaseService;
import utils.Page;
import dao.CaseDAO;
import entity.Case;
import entity.Casestep;
import entity.Module;

public class CaseServiceImpl implements CaseService{
	
	@Autowired
	private CaseDAO sd;
	
	public CaseDAO getSd() {
		return sd;
	}

	public void setSd(CaseDAO sd) {
		this.sd = sd;
	}


	public void add(Case cases) {
		sd.add(cases);
	}
	
	public void update(Case cases) {
		sd.update(cases);
	}
	
	public void delete(Case cases) {
		sd.delete(cases);
	}

	public List<Case> getAllCase() {
		return sd.queryAllCase();
	}
	
	public Case getCaseById(Case cases){
		return sd.queryCaseById(cases.getId());
	}
	
	public int getCaseCount(){
		return sd.queryAllCount();
	}
	
	
	public int getCaseCount(Case cases){
		return sd.queryAllCount(cases);
	}
	
	public List<Case> getAllCaseByPage(Page page,Case cases) {
		return sd.queryAllCaseByPage(page, cases);
	}

	public String getModuleName(String ModuleId) {
		return sd.queryModuleName(ModuleId);
	}

	
	public List<String> getTotalParentModuleNames(Case cases){
		List<String> totalModules = new ArrayList<String>();
		Module parent;
		String module=cases.getModuleId();
		totalModules.add(sd.queryModuleName(module));
		while(true){
			parent=sd.queryParentModule(module);
			if(parent!=null){
				module=String.valueOf(parent.getId());
				totalModules.add(parent.getName());
			}
			else {
				break;
			}
		}
		return totalModules;
	}
	
	public Case getChildModuels(Case cases){
		String totalModules = "";
		List<Module> child;
		String module=cases.getModuleId();
		while(true){
			child=sd.queryChildModule(module);
			if(child==null){
				return cases;
			}
			else {
				break;
			}
		}
		for(Module m:child){
			totalModules+=m.getId()+",";
		}
		cases.setModuleId(totalModules);
		return cases;
	}

	public List<Case> getCaseByCaseId(List<String> caseIds) {
		return sd.queryCaseByCaseId(caseIds);
	}

	public void addStep(List<Casestep> casesteps) {
		sd.addStep(casesteps);
	}

	public String getMaxVersion(String caseIds) {
		return sd.getMaxVersion(caseIds);
	}

	public List<Casestep> getStep(int caseId, String version) {
		return sd.getStep(caseId, version);
	}

}
