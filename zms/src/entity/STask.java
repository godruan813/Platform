package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 子任务实体
 * 
 * @author jsyangfan
 * 
 */
public class STask implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5704723338889564495L;
	
	private long id;
	private String creater;// 创建者
	private Date startTime;// 起始时间
	private Date endTime;// 结束时间
	private String ip;// 执行机ip
	private long pid;// 子任务id
	private String status;
	private String reportHref;

	
	public String getReportHref() {
		return reportHref;
	}

	public void setReportHref(String reportHref) {
		this.reportHref = reportHref;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreater() {
		return creater;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getIp() {
		return ip;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public STask(int id, String creater, Date startTime, Date endTime,
			String ip, int pid) {
		super();
		this.id = id;
		this.creater = creater;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ip = ip;
		this.pid = pid;
	}

	public STask(){};

	
}
