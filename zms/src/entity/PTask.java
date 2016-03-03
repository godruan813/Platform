package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
/**
 *父任务实体
 *
 */
public class PTask implements Serializable
{
	private static final long serialVersionUID = -3898818817110165741L;
	private long id;//任务id
	private String taskname;//任务名称
	private String ip;//
	private String caseIds;//用例ids
	private String creater;//任务创建者
	private Date opertime;//任务创建时间
	private String env;//任务创建时间
	private Set<STask> sTasks;//关联多个子任务
	private Computer computer;//关联测试机状态
	private String browser;//
	private String taskType;//

	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public Set<STask> getsTasks() {
		return sTasks;
	}
	public void setsTasks(Set<STask> sTasks) {
		this.sTasks = sTasks;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getCaseIds() {
		return caseIds;
	}
	public void setCaseIds(String caseIds) {
		this.caseIds = caseIds;
	}
	public Date getOpertime() {
		return opertime;
	}
	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}
	public Set<STask> getSTasks() {
		return sTasks;
	}
	public void setAutoSTasks(Set<STask> sTasks) {
		this.sTasks = sTasks;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}

	
}
