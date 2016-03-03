package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import service.CaseProcessService;
import service.CaseService;
import service.PTaskService;
import service.STaskService;
import utils.Client;
import utils.Constants;
import utils.JSONUtils;
import utils.Page;
import utils.PageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Case;
import entity.Computer;
import entity.PTask;
import entity.STask;
import entity.User;


public class TaskAction extends ActionSupport {
	
    private static final long serialVersionUID = 1L;  
    private Map<String,Object> dataMap; 
    private String dataJson;
    @Autowired
    private PTaskService pts;
    @Autowired
    private STaskService sts;
    @Autowired
    private CaseService cs;
    @Autowired
    private CaseProcessService cps;
	private STask task;
	private PTask pt;
	private Case cases;
	private int currentPage;
    private String key = "Just see see";  

    
    public PTask getPt() {
		return pt;
	}

	public void setPt(PTask pt) {
		this.pt = pt;
	}

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	public STask getTask() {
		return task;
	}

	public void setTask(STask task) {
		this.task = task;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
    
    public String getDataJson() {  
        return dataJson;  
    }  
    
	public String getAllSTaskByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		Page page = PageUtil.createPage(10, sts.getSTaskCount(task), this.getCurrentPage());
		List<STask> list=sts.getAllSTaskByPage(page, task);
		getSession().put("condition", task);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}
	
	public String getAllSTaskByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		STask sTask=(STask)getSession().get("condition");
		Page page = PageUtil.createPage(10, sts.getSTaskCount(sTask), this.getCurrentPage());
		List<STask> list=sts.getAllSTaskByPage(page, sTask);
		request.setAttribute("page", page);
		request.setAttribute("case", list);
		return this.SUCCESS;
	}
	
	public String addTask(){
		Map session=getSession();
		int caseId=cases.getId();
		Case c=cs.getCaseById(cases);
		List<Case> taskList=(List<Case>) session.get("sTask");
		for(Case case1:taskList){
			int cId=case1.getId();
			if(caseId==cId){
				dataJson="fail";
				return SUCCESS;
			}
		}
		taskList.add(c);
		session.put("sTask", taskList);
		dataJson="success";
		return SUCCESS;
		
	}
	
	public String delTask(){
		Map session=getSession();
		int caseId=cases.getId();
		Case c=cs.getCaseById(cases);
		List<Case> taskList=(List<Case>) session.get("sTask");
		for(Case case1:taskList){
			int cId=case1.getId();
			if(caseId==cId){
				taskList.remove(case1);
				session.put("sTask", taskList);
				dataJson="success";
				return SUCCESS;
			}
		}
		dataJson="fail";
		return SUCCESS;
	}
	
	public String delAllTask(){
		Map session=getSession();
		List<Case> taskList=new ArrayList<Case>();
		session.put("sTask", taskList);
		dataJson="success";
		return SUCCESS;
	}
    
    @JSON(serialize=false)  
    public String getKey() {  
        return key;  
    } 
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	
	public String getAllPTaskByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		Page page = PageUtil.createPage(10, pts.getPTaskCount(pt), this.getCurrentPage());
		List<PTask> list=pts.getAllPTaskByPage(page, pt);
		getSession().put("condition", task);
		request.setAttribute("page", page);
		request.setAttribute("ptask", list);
		return this.SUCCESS;
	}
	
	public String getAllPTaskByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		STask sTask=(STask)getSession().get("condition");
		Page page = PageUtil.createPage(10, pts.getPTaskCount(pt), this.getCurrentPage());
		List<PTask> list=pts.getAllPTaskByPage(page, pt);
		request.setAttribute("page", page);
		request.setAttribute("ptask", list);
		return this.SUCCESS;
	}
	
	public String getTasks(){
		List<STask> list=sts.getSTaskByPId(pt);
		dataJson=JSONArray.fromObject(list).toString();
		return SUCCESS;
	}
	
	public String excute(){
		HttpServletRequest request = ServletActionContext.getRequest();
        String caseId = request.getParameter("caseId");
        String creater = ((User)getSession().get("user")).getUsername();
		List<Computer> availableIp =cps.getIpToRun(Constants.COMPUTER_STATUS_FREE,Constants.COMPUTER_STATUS_DONE,Constants.COMPUTER_STATUS_TERMINATE);
		if(availableIp.size()<=0||availableIp==null){
			dataJson="目前没有空闲的测试机可以运行,请稍后再试";
			return "fail";
		}
		PTask autoPTask = new PTask();
		autoPTask.setCaseIds(caseId);
		autoPTask.setCreater(creater); 	
		autoPTask.setOpertime(new Date());
		autoPTask.setTaskname("测试");
		autoPTask.setIp("192.168.0.199");
		autoPTask.setBrowser("*firefox");
		autoPTask.setEnv("1");
		autoPTask.setTaskType("1");
		long pid=pts.add(autoPTask);
		Map<String, String> caseInfo=cps.getCaseInfo(caseId.split(","));//根据caseid解析获取case运行信息
		caseInfo.put("ip", "192.168.0.199");
		Client client=cps.socketProcess(caseInfo,(int)pid,creater,"1", "*firefox");
		JSONObject jobj=new JSONObject();
		jobj.put("pid", String.valueOf(pid));
		//通知列表
		String noticed=(String)getSession().get("notice");
		if(StringUtils.isEmpty(noticed)){
			getSession().put("notice", String.valueOf(pid));
		}
		else {
			getSession().put("notice", noticed+","+String.valueOf(pid));
		}
		dataJson=jobj.toString();
		return SUCCESS;
	}
	
	public String getRunning(){
		 //HttpServletRequest request = ServletActionContext.getRequest();
		// String pids = request.getParameter("pids");		 
		 String pids=(String)getSession().get("notice");
		 String creater = ((User)getSession().get("user")).getUsername();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Calendar calendar= Calendar.getInstance(); 
		 Date now = calendar.getTime();
		 calendar.add(Calendar.HOUR, -10);
		 Date ago = calendar.getTime();
		 List<PTask> pTasks;
		 if(StringUtils.isEmpty(pids)){
			 pTasks=new ArrayList<PTask>();
		 }
		 else{
			 pTasks=pts.getPTaskOfhours(ago, now, creater,pids);
		 }
		 dataJson=JSONUtils.toJSon(pTasks);
		 return SUCCESS;
	}
	
	
}
