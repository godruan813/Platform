package entity;
// default package



/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student  implements java.io.Serializable {


    // Fields    

	 private Integer pk;
     private String sid;
     private String password="1";
     private String name;
     private Integer gender;
     private Integer age;


    // Constructors

    /** default constructor */
    public Student() {
    }

    
    /** full constructor */
    public Student(Integer pk,String sid, String password, String name, Integer gender, Integer age) {
    	this.pk=pk;
        this.sid = sid;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

   
    // Property accessors
    
    
    public String getSid() {
        return this.sid;
    }
    
    public Integer getPk() {
		return pk;
	}


	public void setPk(Integer pk) {
		this.pk = pk;
	}


	public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return this.gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
   








}