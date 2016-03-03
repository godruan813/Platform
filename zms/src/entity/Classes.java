package entity;
// default package



/**
 * Classes entity. @author MyEclipse Persistence Tools
 */

public class Classes  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer grade;
     private Integer teacher;
     private Integer type;


    // Constructors

    /** default constructor */
    public Classes() {
    }

    
    /** full constructor */
    public Classes(String name, Integer grade, Integer teacher, Integer type) {
        this.name = name;
        this.grade = grade;
        this.teacher = teacher;
        this.type = type;
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

    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
   








}