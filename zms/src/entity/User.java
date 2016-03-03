package entity;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String position;
	private String dept;
	private String nickname;

	
	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String password, String position, String dept) {
		this.username = username;
		this.password = password;
		this.position = position;
		this.dept = dept;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}