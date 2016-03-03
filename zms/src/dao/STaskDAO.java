package dao;

import java.util.List;

import utils.Page;
import entity.PTask;
import entity.STask;

public interface STaskDAO {

	STask querySTaskById(long id);
	List<STask> queryAllSTaskByPId(long pId);
	void add(STask sTask);
	void update(STask sTask);
	void delete(STask sTask);
	List<STask> queryAllSTaskByPage(final Page page,final STask sTask);
	int queryAllCount();
	int queryAllCount(STask sTask);
	public List<STask> queryAllSTask();
	List<PTask> queryPTask(long id);
}
