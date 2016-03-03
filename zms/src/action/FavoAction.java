package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import service.CaseService;
import service.FavoService;
import utils.Page;
import utils.PageUtil;
import entity.Case;
import entity.Favo;
import entity.User;

public class FavoAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map<String,Object> dataMap; 
    private String dataJson;
    @Autowired
    private CaseService caseService;
    @Autowired
    private FavoService favoService;
	private Case cases;
	private Favo favo;
	private int currentPage;
    private String key = "Just see see";  
    private String qId;
    
    
    public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public FavoService getFavoService() {
		return favoService;
	}

	public void setFavoService(FavoService favoService) {
		this.favoService = favoService;
	}

	public Favo getFavo() {
		return favo;
	}

	public void setFavo(Favo favo) {
		this.favo = favo;
	}

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
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
    
    public String favoAdd(){
    	String username=((User)(getSession().get("user"))).getUsername();
    	Favo oldFavo=favoService.getFavosByUsername(username);
    	if(oldFavo==null){
    		Favo newFavo=new Favo();
    		newFavo.setCaseIds(favo.getCaseIds());
    		newFavo.setUsername(username);
    		favoService.update(newFavo);
    	}
    	else if(StringUtils.isEmpty(oldFavo.getCaseIds())) {
    		oldFavo.setCaseIds(favo.getCaseIds());
    		favoService.update(oldFavo);
		}
    	else {
    		oldFavo.setCaseIds(oldFavo.getCaseIds()+","+favo.getCaseIds());
    		favoService.update(oldFavo);
		}
    	dataJson="success";
    	return SUCCESS;
    }
    
    public String favoDelete(){
    	String username=((User)(getSession().get("user"))).getUsername();
    	Favo oldFavo=favoService.getFavosByUsername(username);
    	List<String> ids=new ArrayList<String>();
    	//转换后会抛出异常,因此用addALL
  //  	List<String> ids=Arrays.asList(oldFavo.getCaseIds().split(","));
    	Collections.addAll(ids, oldFavo.getCaseIds().split(","));
    	ids.remove(favo.getCaseIds());
		oldFavo.setCaseIds(StringUtils.join(ids, ","));
		favoService.update(oldFavo);
		dataJson="success";
    	return SUCCESS;
    }
    
	public String getAllFavoByCondition(){
		if(cases!=null){
			qId=cases.getqId();
		}
		List<Case> list = new ArrayList<Case>();
		HttpServletRequest request = ServletActionContext.getRequest();
		Favo favo=favoService.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
		if(favo==null){
		}
		else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
		else {
			String ids=favo.getCaseIds();
			List<String> idList=Arrays.asList(ids.split(","));
			list=caseService.getCaseByCaseId(idList);
			list=(list==null?new ArrayList<Case>():list);
			if(!StringUtils.isEmpty(qId)){
				if(idList.contains(qId)){
					for(Case c:list){
						if(String.valueOf(c.getId()).equals(qId)){
							list=new ArrayList<Case>();
							list.add(c);
							break;
						}
					}
				}
				else {
					list=new ArrayList<Case>();
				}
			}
		}
		Page page = PageUtil.createPage(10, list.size(), this.getCurrentPage());
		list=PageUtil.getListPage(page, list);
		getSession().put("condition", cases);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}
	
	public String getAllFavoByConditionP(){
		List<Case> list = new ArrayList<Case>();
		HttpServletRequest request = ServletActionContext.getRequest();
		Favo favo=favoService.getFavosByUsername(((User)(getSession().get("user"))).getUsername());
		if(favo==null){
		}
		else if(StringUtils.isEmpty(favo.getCaseIds())) {
		}
		else {
			String ids=favo.getCaseIds();
			List<String> idList=Arrays.asList(ids.split(","));
			list=caseService.getCaseByCaseId(idList);
			
		}
		Page page = PageUtil.createPage(10, list.size(), this.getCurrentPage());
		list=PageUtil.getListPage(page, list);
		getSession().put("condition", cases);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}
    
    
	
}
