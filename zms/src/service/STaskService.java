package service;

import java.util.List;

import utils.Page;
import entity.PTask;
import entity.STask;

public interface STaskService {

	void add(STask sTask);
	void update(STask sTask);
	void delete(STask sTask);
	STask getSTaskById(STask sTask);
	List<STask> getSTaskByPId(PTask pTask);
	List<STask> getAllSTask();
	List<STask> getAllSTaskByPage(Page page,STask sTask);
	int getSTaskCount();
	int getSTaskCount(STask stask);


}
