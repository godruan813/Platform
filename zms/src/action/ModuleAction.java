package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import service.ModuleService;
import service.UserService;
import utils.Page;
import utils.PageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

import entity.Module;
import entity.User;

public class ModuleAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Module module;
	@Autowired
	private ModuleService moduleService;
	private String dataJson;
	private Map<String,Object> dataMap; 
	private int currentPage;
    private String key = "Just see see";  
    private String mId;
    
    
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public ModuleService getModuleService() {
		return moduleService;
	}
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
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
    
    @JSON(serialize=false)  
    public String getKey() {  
        return key;  
    } 
	
	public String getModules(){
		Module m=moduleService.getModule(module.getId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("module", m);
		return SUCCESS;
	}
	
	public String moduleModify(){
		moduleService.update(module);
		this.setmId(String.valueOf(module.getId()));
        dataJson="success";
        return SUCCESS;  	
    }
	
	public String moduleDelete(){
		List<Module> list=moduleService.getChild(module);
		List<Module> toDelete=new ArrayList<Module>();
		toDelete.add(module);
		if(list.size()>0){
			toDelete=deleteAll(list, toDelete);
		}
		moduleService.deleteAll(toDelete);
        dataJson="success";
        return SUCCESS;
	}
	
	private List<Module> deleteAll(List<Module> modules,List<Module> toDelete){
		for(Module m:modules){
			toDelete.add(m);
			List<Module> list=moduleService.getChild(m);
			if(list.size()>0){
				deleteAll(list,toDelete);
			}
		}
		return toDelete;
	}

}
