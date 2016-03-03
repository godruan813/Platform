package entity;



/**
 * TbCasestep entity. @author MyEclipse Persistence Tools
 */

public class Casestep  implements java.io.Serializable, java.lang.Comparable{


    // Fields    

     private Integer id;
     private Integer caseId;
     private String stepinfo;
     private String expect;
     private String version;
     
     //
     private Integer stepId;
     
    // Constructors

    /** default constructor */
    public Casestep() {
    }

    
    /** full constructor */
    // Property accessors

    
    
    
    public Integer getId() {
        return this.id;
    }
    
    public String getStepinfo() {
		return stepinfo;
	}


	public void setStepinfo(String stepinfo) {
		this.stepinfo = stepinfo;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public Integer getStepId() {
		return stepId;
	}


	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}


	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return this.caseId;
    }
    
    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getExpect() {
        return this.expect;
    }
    
    public void setExpect(String expect) {
        this.expect = expect;
    }


	public int compareTo(Object arg0) {
		Casestep cs=(Casestep)arg0;
		if(this.stepId>cs.getStepId()){
			return 1;
		}
		return -1;
	}
   








}