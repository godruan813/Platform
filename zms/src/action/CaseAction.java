package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import service.CaseService;
import service.FavoService;
import service.ModuleService;
import utils.Page;
import utils.PageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Case;
import entity.Casestep;
import entity.Favo;
import entity.Module;
import entity.User;


public class CaseAction extends ActionSupport {
	
    private static final long serialVersionUID = 1L;  
    private Map<String,Object> dataMap; 
    private String dataJson;
    @Autowired
    private CaseService cs;
    @Autowired 
    private FavoService fs;
    @Autowired
    private ModuleService ms;
	private int currentPage;
    private String key = "Just see see";  
    private String[] expects;
    private String[] steps;
    private Case cases;
    private String mId;
    
    
	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	public String[] getExpects() {
		return expects;
	}

	public void setExpects(String[] expects) {
		this.expects = expects;
	}

	public String[] getSteps() {
		return steps;
	}

	public void setSteps(String[] steps) {
		this.steps = steps;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

    @JSON(serialize=false)  
    public String getKey() {  
        return key;  
    } 
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
    
    public String getDataJson() {  
        return dataJson;  
    }  
    
	public String getAllCaseByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//cases.setMid(mid);
		//ServletContext app = ServletActionContext.getServletContext();
		List<String> module=cs.getTotalParentModuleNames(cases);
		request.setAttribute("moduleId", cases.getModuleId());
		Collections.reverse(module);
		cases=cs.getChildModuels(cases);
		Page page = PageUtil.createPage(10, cs.getCaseCount(cases), this.getCurrentPage());
		List<Case> list=cs.getAllCaseByPage(page, cases);
		Favo favo=fs.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
    	if(favo==null){
    	}
    	else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
    	else {
    		String ids=favo.getCaseIds();
    		List<String> idList=Arrays.asList(ids.split(","));
    		for(Case c:list){
    			if(idList.contains(String.valueOf(c.getId()))){
    				c.setIsFavo("1");
    			}
    		}
		}
		getSession().put("condition", cases);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		request.setAttribute("module", module);
		return this.SUCCESS;
	}
	
	public String getAllCaseByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		Case cases=(Case)getSession().get("condition");
		cases=cs.getChildModuels(cases);
		Page page = PageUtil.createPage(10, cs.getCaseCount(cases), this.getCurrentPage());
		List<Case> list=cs.getAllCaseByPage(page, cases);
		Favo favo=fs.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
    	if(favo==null){
    	}
    	else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
    	else {
    		String ids=favo.getCaseIds();
    		List<String> idList=Arrays.asList(ids.split(","));
    		for(Case c:list){
    			if(idList.contains(String.valueOf(c.getId()))){
    				c.setIsFavo("1");
    			}
    		}
		}
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}
 
	public String getAllfCaseByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		List<String> module=cs.getTotalParentModuleNames(cases);
		request.setAttribute("moduleId", cases.getModuleId());
		Collections.reverse(module);
		cases=cs.getChildModuels(cases);
		Page page = PageUtil.createPage(10, cs.getCaseCount(cases), this.getCurrentPage());
		List<Case> list=cs.getAllCaseByPage(page, cases);
		Favo favo=fs.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
    	if(favo==null){
    	}
    	else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
    	else {
    		String ids=favo.getCaseIds();
    		List<String> idList=Arrays.asList(ids.split(","));
    		for(Case c:list){
    			if(idList.contains(String.valueOf(c.getId()))){
    				c.setIsFavo("1");
    			}
    		}
		}
		getSession().put("condition", cases);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		request.setAttribute("module", module);
		return this.SUCCESS;
	}
	
	public String getAllfCaseByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		Case cases=(Case)getSession().get("condition");
		cases=cs.getChildModuels(cases);
		Page page = PageUtil.createPage(10, cs.getCaseCount(cases), this.getCurrentPage());
		List<Case> list=cs.getAllCaseByPage(page, cases);
		Favo favo=fs.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
    	if(favo==null){
    	}
    	else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
    	else {
    		String ids=favo.getCaseIds();
    		List<String> idList=Arrays.asList(ids.split(","));
    		for(Case c:list){
    			if(idList.contains(String.valueOf(c.getId()))){
    				c.setIsFavo("1");
    			}
    		}
		}
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}	
	
	public String gotoCaseModify(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int caseId=cases.getId();
		cases=cs.getCaseById(cases);
		String moduleId ="";
		if(cases==null){
			moduleId= request.getParameter("m");
			cases=new Case();
			request.setAttribute("step", new ArrayList<String>());
			cases.setPriority("3");
		}
		else {
			moduleId=cases.getModuleId();
			String maxVersion=cs.getMaxVersion(String.valueOf(cases.getId()));
			if(maxVersion==null){
				request.setAttribute("step", new ArrayList<String>());
			}
			else {
				List<Casestep> list=cs.getStep(cases.getId(), maxVersion);
				request.setAttribute("step", list);
			}
		}
		Module module=ms.getModule(Integer.parseInt(moduleId));
		cases.setMm(module);
		request.setAttribute("cases", cases);
		return SUCCESS;
	}
	
	public String caseAdd(){
		String[] ex=expects;
		String[] st=steps;
		cases.setLastModify(new Date());
		cs.add(cases);
		List<Casestep> list=new ArrayList<Casestep>();
		Casestep caseStep=new Casestep();
		for(int i=0;i<ex.length;i++){
			caseStep=new Casestep();
			caseStep.setExpect(ex[i]);
			caseStep.setStepinfo(st[i]);
			caseStep.setCaseId(cases.getId());
			caseStep.setVersion("1");
			list.add(caseStep);
		}
		cs.addStep(list);
		this.setmId(cases.getModuleId());
		return SUCCESS;
	}
	
	
	public String caseModify(){
		String[] ex=expects;
		String[] st=steps;
		cases.setLastModify(new Date());
		cs.update(cases);
		String maxVersion=cs.getMaxVersion(String.valueOf(cases.getId()));
		int newVersion=Integer.parseInt(maxVersion)+1;
		List<Casestep> list=new ArrayList<Casestep>();
		Casestep caseStep=new Casestep();
		for(int i=0;i<ex.length;i++){
			caseStep=new Casestep();
			caseStep.setCaseId(cases.getId());
			caseStep.setExpect(ex[i]);
			caseStep.setStepinfo(st[i]);
			caseStep.setStepId(i);
			caseStep.setVersion(String.valueOf(newVersion));
			list.add(caseStep);
		}
		cs.addStep(list);
		HttpServletRequest request = ServletActionContext.getRequest();
		Module module=ms.getModule(Integer.parseInt(cases.getModuleId()));
		cases.setMm(module);
		request.setAttribute("cases", cases);
		request.setAttribute("step", list);
        dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);  
        return SUCCESS;  	
	}
	
	public String caseDeleteJ(){
		cs.delete(cases);
        dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);  
        return SUCCESS;  	
    }
	
	public String caseAllDeleteJ(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] caseIds=(request.getParameter("cid")).split(",");
		for(int i=0;i<caseIds.length;i++){
			Case c=new Case();
			c.setId(Integer.parseInt(caseIds[i]));
			cs.delete(c);
		}
        dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);  
        return SUCCESS;  	
    }
	
}
