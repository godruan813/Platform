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
    	     * �ڵ���getWriter֮ǰδ���ñ���(�ȵ���setContentType����setCharacterEncoding�������ñ���), 
    	     * HttpServletResponse��᷵��һ����Ĭ�ϵı���(��ISO-8859-1)�����PrintWriterʵ���������ͻ� 
    	     * ����������롣�������ñ���ʱ�����ڵ���getWriter֮ǰ����,��Ȼ����Ч�ġ� 
    	     * */  
    	    response.setContentType("text/html;charset=utf-8");  
    	    //response.setCharacterEncoding("UTF-8");  
    	    PrintWriter out = response.getWriter();  
    	    //JSON�ڴ��ݹ���������ͨ�ַ�����ʽ���ݵģ������ƴ��һ��������  
    	    Student stu=new Student();
    	    stu.setAge(1);
    	    stu.setSid("22");
    	    out.println(JSONObject.fromObject(stu).toString());  
    	    out.flush();  
    	    out.close();  
    } 
}