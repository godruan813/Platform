package service;

import java.util.List;

import utils.Page;
import entity.Timer;

public interface TimerService {

	
	void add(Timer Timer);
	void update(Timer Timer);
	void delete(Timer Timer);
	Timer getTimerById(Timer Timer);
	List<Timer> getAllTimer();
	List<Timer> getAllTimerByPage(Page page,Timer Timer);
	int getTimerCount();
	int getTimerCount(Timer Timer);
}
