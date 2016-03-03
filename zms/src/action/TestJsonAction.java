package action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

import entity.Student;

public class TestJsonAction extends ActionSupport{  
    private static final long serialVersionUID = 1L;  
    
    private Map<String,Object> dataMap;  
    private String key = "Just see see";  
      
    public String json() {  
        // dataMap�е����ݽ��ᱻStruts2ת����JSON�ַ�������������Ҫ��������е�����  
        dataMap = new HashMap<String, Object>();
        Student student=new Student();
        dataMap.put("student", student);  
        // ����һ���Ƿ�����ɹ��ı�ʶ  
        dataMap.put("success", true);  
        // ���ؽ��  
        return SUCCESS;  
    }  
  
    public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
  
    //����key���Բ���Ϊjson�����ݷ���  
    @JSON(serialize=false)  
    public String getKey() {  
        return key;  
    } 
}