package serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import service.PTaskService;
import utils.Page;
import dao.PTaskDAO;
import entity.PTask;

public class PTaskServiceImpl implements PTaskService{
	
	@Autowired
	private PTaskDAO pd;
	
	public PTaskDAO getSd() {
		return pd;
	}

	public void setSd(PTaskDAO pd) {
		this.pd = pd;
	}


	public long add(PTask pTask) {
		pd.add(pTask);
		return (pTask.getId());
	}
	
	public void update(PTask pTask) {
		pd.update(pTask);
	}
	
	public void delete(PTask pTask) {
		pd.delete(pTask);
	}

	public List<PTask> getAllPTask() {
		return pd.queryAllPTask();
	}
	
	public PTask getPTaskById(PTask pTask){
		return pd.queryPTaskById(pTask.getId());
	}
	
	public int getPTaskCount(){
		return pd.queryAllCount();
	}
	
	public int getPTaskCount(PTask pTask){
		return pd.queryAllCount(pTask);
	}
	
	public List<PTask> getAllPTaskByPage(Page page,PTask pTask) {
		return pd.queryAllPTaskByPage(page, pTask);
	}

	public List<PTask> getPTaskOfhours(Date from, Date to, String user,String type) {
		return pd.getPTaskOfhours(from, to, user,type);
	}

}
