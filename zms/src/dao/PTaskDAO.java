package dao;

import java.util.Date;
import java.util.List;

import utils.Page;
import entity.Computer;
import entity.PTask;

public interface PTaskDAO {

	PTask queryPTaskById(long id);
	List<PTask> queryAllPTask();
	void add(PTask pTask);
	void update(PTask pTask);
	void delete(PTask pTask);
	List<PTask> queryAllPTaskByPage(final Page page,final PTask pTask);
	int queryAllCount();
	int queryAllCount(PTask pTask);
	PTask queryPTaskByIds(long id);
	Computer queryPTaskBycom(long id);
	List<PTask> getPTaskOfhours(Date from, Date to, String user,String type);
}
