package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.TimerDao;

import entity.Timer;
import service.TimerService;
import utils.Page;

public class TimerServiceImpl implements TimerService {

	@Autowired
	private TimerDao timerDao;
	
	public void add(Timer timer) {
		timerDao.add(timer);
	}

	public void update(Timer timer) {
		timerDao.update(timer);

	}

	public void delete(Timer timer) {
		timerDao.delete(timer);
	}

	public Timer getTimerById(Timer timer) {
		// TODO Auto-generated method stub
		if(timer==null) return null;
		return timerDao.queryTimerById(timer.getId());
	}

	public List<Timer> getAllTimer() {
		// TODO Auto-generated method stub
		return timerDao.queryAllTimer();
	}

	public List<Timer> getAllTimerByPage(Page page, Timer Timer) {
		// TODO Auto-generated method stub
		return timerDao.queryAllTimerByPage(page, Timer);
	}

	public int getTimerCount() {
		// TODO Auto-generated method stub
		return timerDao.queryAllCount();
	}

	public int getTimerCount(Timer timer) {
		return timerDao.queryAllCount(timer);
	}

}
