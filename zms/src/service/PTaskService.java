package service;

import java.util.Date;
import java.util.List;

import utils.Page;

import entity.PTask;

public interface PTaskService {

	long add(PTask pTask);
	void update(PTask pTask);
	void delete(PTask pTask);
	PTask getPTaskById(PTask pTask);
	List<PTask> getAllPTask();
	List<PTask> getAllPTaskByPage(Page page,PTask pTask);
	int getPTaskCount();
	int getPTaskCount(PTask pTask);
	List<PTask> getPTaskOfhours(Date from,Date to,String user,String type);

}
