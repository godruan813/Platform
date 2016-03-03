package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import antlr.CSharpCodeGenerator;

import dao.CaseDAO;
import dao.ModuleDAO;
import dao.PTaskDAO;
import dao.PlatFormDao;
import dao.STaskDAO;
import dao.UserDao;
import entity.Casestep;
import entity.Computer;
import entity.PTask;
import entity.STask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TTT {
	
	@Autowired 
	private STaskDAO sD;
	@Autowired 
	private PTaskDAO pD;
	@Autowired
	private PlatFormDao pfd;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModuleDAO moduleDAO;
	@Autowired
	private CaseDAO caseDAO;
	@Test
	public void test() {
		
		//System.out.println(sD.queryAllCount());
		List list=sD.queryPTask(1l);
		System.out.println(list);	
		System.out.println(list);	
		}
	
	@Test
	public void test1() {
		
		//System.out.println(sD.queryAllCount());
		PTask list=pD.queryPTaskByIds(1l);
		Computer com=pD.queryPTaskBycom(1l);
		System.out.println(list);	
		System.out.println(list);	
		System.out.println(com);	
		System.out.println(com);	
	}
	
	@Test
	public void test2() {
		
		//System.out.println(sD.queryAllCount());
		PTask pt=new PTask();
		pt.setCaseIds("1");
		pt.setCreater("2");
		pt.setTaskname("3");
		
		System.out.println(pfd.savePTask(pt));
	}
	
	@Test
	public void test3() {
		
		//System.out.println(sD.queryAllCount());
		pfd.updateComputerStatus("192.168.0.142","555");
	}
	
	@Test
	public void test4() {
		
		//System.out.println(sD.queryAllCount());
		List list=pfd.findcasesByIds(new String[]{"1","2"});
		System.out.println();
	}
	
	
	@Test
	public void test5() {
		
		//System.out.println(sD.queryAllCount());
		pfd.deleteStep(11);
	}
	
	@Test
	public void test6(){
		System.out.println(userDao.loginQuery("»Ó •µœ"));
	}
	
	@Test
	public void test7(){
		System.out.println(moduleDAO.queryAllModules());
	}
	
	@Test
	public void test8(){
		
		//System.out.println(caseDAO.getMaxVersion("1")==null);
		Casestep c1=new Casestep();
		c1.setCaseId(3);
		c1.setStepinfo("hehe");
		c1.setVersion("1");
		Casestep c2=new Casestep();
		c2.setCaseId(3);
		c2.setStepinfo("hehe");
		c2.setVersion("1");
		List<Casestep> l=new ArrayList<Casestep>();
		l.add(c1);
		l.add(c2);
		caseDAO.addStep(l);
	}
	
	@Test
	public void ttt(){
		STask s1=new STask();
		STask s2=new STask();
		List<STask> list=new ArrayList<STask>();
		list.add(s1);
		list.add(s2);
		System.out.println(JSONArray.fromObject(list).toString());
	}

}
