package dao;

import java.util.List;

import utils.Page;
import entity.Timer;

public interface TimerDao {
	
	List<Timer> queryAllTimer();
	Timer queryTimerById(int id);
	void add(Timer Timer);
	void update(Timer Timer);
	void delete(Timer Timer);
	List<Timer> queryAllTimerByPage(final Page page,final Timer Timer);
	int queryAllCount();
	int queryAllCount(Timer Timer);

}
