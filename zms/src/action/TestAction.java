package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Student;

public class TestAction extends ActionSupport{  
    private static final long serialVersionUID = 1L;  
      
      
    public void write() throws Exception {  
    	  HttpServletResponse response=ServletActionContext.getResponse();  
    	    /* 
    	     * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码), 
    	     * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会 
    	     * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。 
    	     * */  
    	    response.setContentType("text/html;charset=utf-8");  
    	    //response.setCharacterEncoding("UTF-8");  
    	    PrintWriter out = response.getWriter();  
    	    //JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试  
    	    Student stu=new Student();
    	    stu.setAge(1);
    	    stu.setSid("22");
    	    out.println(JSONObject.fromObject(stu).toString());  
    	    out.flush();  
    	    out.close();  
    } 
}