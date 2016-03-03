package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import service.STaskService;
import utils.Page;
import dao.STaskDAO;
import daoImpl.STaskDAOImpl;
import entity.PTask;
import entity.STask;

public class STaskServiceImpl implements STaskService{
	
	@Autowired
	private STaskDAO sd;
	
	public STaskDAO getSd() {
		return sd;
	}

	public void setSd(STaskDAO sd) {
		this.sd = sd;
	}


	public void add(STask sTask) {
		sd.add(sTask);
	}
	
	public void update(STask sTask) {
		sd.update(sTask);
	}
	
	public void delete(STask sTask) {
		sd.delete(sTask);
	}

	public List<STask> getAllSTask() {
		return sd.queryAllSTask();
	}
	
	public STask getSTaskById(STask sTask){
		return sd.querySTaskById(sTask.getId());
	}
	
	public int getSTaskCount(){
		return sd.queryAllCount();
	}
	
	public int getSTaskCount(STask sTask){
		return sd.queryAllCount(sTask);
	}
	
	public List<STask> getAllSTaskByPage(Page page,STask sTask) {
		return sd.queryAllSTaskByPage(page, sTask);
	}

	public List<STask> getSTaskByPId(PTask pTask) {
		return sd.queryAllSTaskByPId(pTask.getId());
	}
}
