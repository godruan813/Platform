package filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.Case;

public class Listener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		List<Case> taskList=new ArrayList<Case>();
		session.setAttribute("sTask", taskList);
		session.setAttribute("notice", "");
		System.out.println("***************************************************************************************start");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		session.removeAttribute("sTask");
		session.invalidate();
		System.out.println("***************************************************************************************boom");
		
	}



	
}
