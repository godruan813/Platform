package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import service.ModuleService;
import service.UserService;
import utils.Page;
import utils.PageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Module;
import entity.User;

public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private User user;
	private UserService userService;
	private ModuleService moduleService;
	private String dataJson;
	private Map<String,Object> dataMap; 
	private int currentPage;
    private String key = "Just see see";  

    
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public String login(){
		User u=userService.queryUser(user);
		if(user.getPassword().equals(u.getPassword())){
			getSession().put("user", u);
			return SUCCESS;
		}
		else {
			this.addActionError("username invalid");
			//ServletActionContext.getRequest().setAttribute("msg", "用户名或密码错误");
			return INPUT;
		}
	}
	
	public String logout(){
		getSession().remove("user");
		return SUCCESS;
	}
	
	public String getZtree(){
		JSONArray ja=new JSONArray();
		JSONObject jo = new JSONObject();
		List<Module> list=moduleService.getAllModules();
		String target="main";
		String t="";
		for (Module m:list) {
			int id=m.getId();
			String name=m.getName();
			String module=String.valueOf(m.getId());
			int pId=m.getParent();
			jo = new JSONObject();
			jo.put("id", id);
			jo.put("name", name);
			jo.put("module", module);
			jo.put("pId", pId);
			jo.put("t", t);
			jo.put("target", target);
			ja.add(jo);
		}
		dataJson=ja.toString();
		return SUCCESS;
	}
	

	public String userAdd(){
		userService.add(user);
		return this.SUCCESS;
	}
	
	public String gotoUserModify(){
		User u=userService.getUserById(user);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("user", u);
		return this.SUCCESS;
	}
	
	public String userModify(){
		userService.update(user);
		return this.SUCCESS;
	}
	
	public String userDelete(){
		userService.delete(user);
		return this.SUCCESS;
	}
	
	
	public String userDeleteJ(){
		userService.delete(user);
        dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);  
        return SUCCESS;  	
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
	
	public String getAllUser(){
		Page page = PageUtil.createPage(5, userService.getUserCount(), this.getCurrentPage());
		List<User> list=userService.getAllUserByPage(page,null);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		request.setAttribute("user", list);
		return this.SUCCESS;
	}
	
	public String getAllUserByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		Page page = PageUtil.createPage(8, userService.getUserCount(user), this.getCurrentPage());
		List<User> list=userService.getAllUserByPage(page, user);
		getSession().put("condition", user);
//		if(user!=null){
//		System.out.println(user.getSid());}
		request.setAttribute("page", page);
		request.setAttribute("user", list);
		return this.SUCCESS;
	}
	
	public String getAllUserByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		User user=(User)getSession().get("condition");
		Page page = PageUtil.createPage(8, userService.getUserCount(user), this.getCurrentPage());
		List<User> list=userService.getAllUserByPage(page, user);
		request.setAttribute("page", page);
		request.setAttribute("user", list);
		return this.SUCCESS;
	}
	

}
