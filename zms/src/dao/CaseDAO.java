package dao;

import java.util.List;

import utils.Page;
import entity.Case;
import entity.Casestep;
import entity.Module;
import entity.Student;

public interface CaseDAO {

	Case queryCaseById(int id);
	List<Case> queryAllCase();
	void add(Case cases);
	void addStep(List<Casestep> casesteps);
	void update(Case cases);
	void delete(Case cases);
	List<Case> queryAllCaseByPage(final Page page,final Case cases);
	int queryAllCount();
	int queryAllCount(Case cases);
	String queryModuleName(String ModuleId);
	Module queryParentModule(String ModuleId);
	List queryChildModule(String parentModuleId);
	List<Case> queryCaseByCaseId(List<String> caseIds);
	String getMaxVersion(String caseId);
	List<Casestep> getStep(int caseId,String version);
}
