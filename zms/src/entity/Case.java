package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * case信息表
 * @author jsyangfan
 *
 */
/**
 * @author ruanshengdi
 *
 */
public class Case implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int id;
	private String className;
	private String methodName;
	private String ip;
	private String developer;
	private String detail;
	private String remark;
	private String methodSrc;
	private String isFavo ="0";
	private String qId;
	private String title;
	private String keyword;
	private String type;
	private String precondition;
	private String priority;
	private String moduleId;
	private String creater;
	private Date lastModify;
	private Module mm;
	
	
	public Date getLastModify() {
		return lastModify;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Module getMm() {
		return mm;
	}

	public void setMm(Module mm) {
		this.mm = mm;
	}

	private Set<Casestep> casestep;
	public Set<Casestep> getCasestep() {
		return casestep;
	}

	public void setCasestep(Set<Casestep> casestep) {
		this.casestep = casestep;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMethodSrc() {
		return methodSrc;
	}

	public void setMethodSrc(String methodSrc) {
		this.methodSrc = methodSrc;
	}

	public String getIsFavo() {
		return isFavo;
	}

	public void setIsFavo(String isFavo) {
		this.isFavo = isFavo;
	}

	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	public Case(){
		
	}
	
}
