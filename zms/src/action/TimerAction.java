package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import service.TimerService;
import utils.Page;
import utils.PageUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Timer;

public class TimerAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private String dataJson;
	@Autowired
	private TimerService timerService;
	private Map<String,Object> dataMap; 
	private int currentPage;
    private String key = "Just see see";  

    
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public String logout(){
		getSession().remove("timer");
		return SUCCESS;
	}
	
	

	public String timerAdd(){
		timerService.add(timer);
		return SUCCESS;
	}
	
	public String gotoTimerModify(){
		Timer t=timerService.getTimerById(timer);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(t==null){
			t=new Timer();
			t.setStatus("1");
		}
		this.setTimer(t);
		request.setAttribute("timer", t);
		return SUCCESS;
	}
	
	public String timerModify(){
		timerService.add(timer);
		return SUCCESS;
	}
	
	public String timerDelete(){
		timerService.delete(timer);
		return SUCCESS;
	}
	
	
	public String timerDeleteJ(){
		timerService.delete(timer);
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
	
	public String getAllTimer(){
		Page page = PageUtil.createPage(5, timerService.getTimerCount(), this.getCurrentPage());
		List<Timer> list=timerService.getAllTimerByPage(page,null);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		request.setAttribute("timer", list);
		return SUCCESS;
	}
	
	public String getAllTimerByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		Page page = PageUtil.createPage(8, timerService.getTimerCount(timer), this.getCurrentPage());
		List<Timer> list=timerService.getAllTimerByPage(page, timer);
		getSession().put("condition", timer);
//		if(timer!=null){
//		System.out.println(timer.getSid());}
		request.setAttribute("page", page);
		request.setAttribute("timer", list);
		return SUCCESS;
	}
	
	public String getAllTimerByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		Timer timer=(Timer)getSession().get("condition");
		Page page = PageUtil.createPage(8, timerService.getTimerCount(timer), this.getCurrentPage());
		List<Timer> list=timerService.getAllTimerByPage(page, timer);
		request.setAttribute("page", page);
		request.setAttribute("timer", list);
		return SUCCESS;
	}
	
	

}
