package entity;
// default package



/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer age;
     private Integer gender;
     private String telephone;
     private Integer subject;


    // Constructors

    /** default constructor */
    public Teacher() {
    }

    
    /** full constructor */
    public Teacher(String name, Integer age, Integer gender, String telephone, Integer subject) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.telephone = telephone;
        this.subject = subject;
    }

   
    // Property accessors

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

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return this.gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSubject() {
        return this.subject;
    }
    
    public void setSubject(Integer subject) {
        this.subject = subject;
    }
   








}