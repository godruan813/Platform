package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import service.StudentService;
import serviceImpl.StudentServiceImpl;
import utils.FileUtils;
import utils.Page;
import utils.PageUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Student;


public class StudentAction extends ActionSupport {
	
    private static final long serialVersionUID = 1L;  
	private Student student;
	private StudentService ss;
	private String id;
	private String pwd;
	private String dataJson;
	private Map<String,Object> dataMap; 
	private int currentPage;
    private String key = "Just see see";  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentService getSs() {
		return ss;
	}

	public void setSs(StudentService ss) {
		this.ss = ss;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String log(){
		Student stu=new Student();
		stu.setName("阮圣迪");
		getSession().put("user", stu);
		return this.SUCCESS;
	}

	public String studentLogin(){
		Student stu=ss.login(student);
		if(stu!=null){
			if(stu.getPassword().equals(student.getPassword())){
				getSession().put("user", stu);
				getSession().put("error", "");
				return this.SUCCESS;
			}
			else {
				getSession().put("error", "登录错误");
				return INPUT;
			}
		}
		else {
			getSession().put("error", "登录错误");
			return INPUT;
		}
	}
	
	public String studentLogout(){
		getSession().remove("user");
		ServletActionContext.getRequest().getSession().invalidate();
		return this.SUCCESS;
	}
	
	public String studentAdd(){
		ss.add(student);
		return this.SUCCESS;
	}
	
	public String gotoStudentModify(){
		Student stu=ss.getStudentById(student);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("student", stu);
		return this.SUCCESS;
	}
	
	public String studentModify(){
		ss.update(student);
		return this.SUCCESS;
	}
	
	public String studentDelete(){
		ss.delete(student);
		return this.SUCCESS;
	}
	
	public String getMenu(){
		ServletActionContext.getResponse().setHeader( "Access-Control-Allow-Origin", "*" );
		dataJson=FileUtils.readFile(ServletActionContext.getRequest().getRealPath("res/menu.txt"), true);
        // ���ؽ��  
        return SUCCESS;  	
    }
	
	public String studentDeleteJ(){
		ss.delete(student);
        dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);  
        return SUCCESS;  	
    }
	
    public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
    
    public String getDataJson() {  
        return dataJson;  
    }  
    
    @JSON(serialize=false)  
    public String getKey() {  
        return key;  
    } 
	
	public String getAllStudent(){
		Page page = PageUtil.createPage(5, ss.getStudentCount(), this.getCurrentPage());
		List<Student> list=ss.getAllStudentByPage(page,null);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		request.setAttribute("student", list);
		return this.SUCCESS;
	}
	
	public String getAllStudentByCondition(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletContext app = ServletActionContext.getServletContext();
		Page page = PageUtil.createPage(8, ss.getStudentCount(student), this.getCurrentPage());
		List<Student> list=ss.getAllStudentByPage(page, student);
		getSession().put("condition", student);
		if(student!=null){
		System.out.println(student.getSid());}
		request.setAttribute("page", page);
		request.setAttribute("student", list);
		return this.SUCCESS;
	}
	
	public String getAllStudentByConditionP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext app = ServletActionContext.getServletContext();
		Student stu=(Student)getSession().get("condition");
		Page page = PageUtil.createPage(8, ss.getStudentCount(stu), this.getCurrentPage());
		List<Student> list=ss.getAllStudentByPage(page, stu);
		request.setAttribute("page", page);
		request.setAttribute("student", list);
		return this.SUCCESS;
	}
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	
	
}
