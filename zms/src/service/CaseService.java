package service;

import java.util.List;

import utils.Page;

import entity.Case;
import entity.Casestep;

public interface CaseService {

	void add(Case cases);
	void addStep(List<Casestep> casesteps);
	void update(Case cases);
	void delete(Case cases);
	Case getCaseById(Case cases);
	List<Case> getAllCase();
	List<Case> getAllCaseByPage(Page page,Case cases);
	int getCaseCount();
	int getCaseCount(Case cases);
	String getModuleName(String ModuleId);
	List<String> getTotalParentModuleNames(Case cases);
	Case getChildModuels(Case cases);
	List<Case> getCaseByCaseId(List<String> caseIds);
	String getMaxVersion(String caseIds);
	List<Casestep> getStep(int caseId,String version);
}
