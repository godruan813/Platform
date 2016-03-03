package entity;

import java.io.Serializable;
/**
 * 执行机器实体
 * @author jsyangfan
 *
 */
public class Computer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1279857426166682175L;
	private String ip;//执行机
	private String computerStatus;//执行机器的状态 0:空闲,1:准备就绪,2:运行中,5:异常
	private String moduleName;//执行机对应的功能模块
	private String processId;
	 
	 
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComputerStatus() {
		return computerStatus;
	}
	public void setComputerStatus(String computerStatus) {
		this.computerStatus = computerStatus;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
