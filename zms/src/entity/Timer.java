package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TbTimer entity. @author MyEclipse Persistence Tools
 */

public class Timer implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id=0;
	private String name;
	private String expression;
	private String status;
	private String caseId;
	private Date nextexcute;
	private Date lastexcute;
	private String env;
	private String browser;
	private String laststatus;
	
	
	public String getLaststatus() {
		return laststatus;
	}

	public void setLaststatus(String laststatus) {
		this.laststatus = laststatus;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public Date getNextexcute() {
		return nextexcute;
	}

	public void setNextexcute(Date nextexcute) {
		this.nextexcute = nextexcute;
	}

	public Date getLastexcute() {
		return lastexcute;
	}

	public void setLastexcute(Date lastexcute) {
		this.lastexcute = lastexcute;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

}