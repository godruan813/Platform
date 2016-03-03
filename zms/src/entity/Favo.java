package entity;

/**
 * TbFavo entity. @author MyEclipse Persistence Tools
 */

public class Favo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String caseIds;

	// Constructors

	/** default constructor */
	public Favo() {
	}

	/** minimal constructor */
	public Favo(String username) {
		this.username = username;
	}

	/** full constructor */
	public Favo(String username, String caseIds) {
		this.username = username;
		this.caseIds = caseIds;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCaseIds() {
		return this.caseIds;
	}

	public void setCaseIds(String caseIds) {
		this.caseIds = caseIds;
	}

}