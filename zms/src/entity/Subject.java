package entity;
// default package



/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String subject;


    // Constructors

    /** default constructor */
    public Subject() {
    }

    
    /** full constructor */
    public Subject(String subject) {
        this.subject = subject;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
   








}