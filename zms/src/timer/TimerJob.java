package timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;


import utils.Client;
import utils.Constants;


import service.CaseProcessService;
import service.PTaskService;
import service.TimerService;

import entity.Computer;
import entity.PTask;
import entity.Timer;

public class TimerJob {
	
	@Autowired
	TimerService timerService;
	@Autowired
	CaseProcessService caseProcessService;
	@Autowired
	PTaskService pTaskService;
	
	public static Map<String, Client> cls=new HashMap<String, Client>();
	
	public void run(){
		
		List<Timer> timers=timerService.getAllTimer();
		
		for(Timer timer:timers){
			if(timer.getStatus().equals("1")){
				Date next=timer.getNextexcute();
				if(new Date().after(next)&&!StringUtils.isEmpty(timer.getCaseId())){
					excute(timer);
				}
			}
		}
		
	}
	
	public void excute(Timer timer){
		Date lastDate=new Date();
		CronSequenceGenerator csg = new CronSequenceGenerator(timer.getExpression(), TimeZone.getTimeZone("Asia/Shanghai"));
		Date nextexcute=csg.next(new Date());
		try {
			List<Computer> availableIp;
			while (true) {
				availableIp=caseProcessService.getIpToRun(Constants.COMPUTER_STATUS_FREE,Constants.COMPUTER_STATUS_DONE,Constants.COMPUTER_STATUS_TERMINATE);
				if(availableIp.size()<=0||availableIp==null){
					Thread.sleep(10000);
					System.out.println("等待空闲测试机...");
					continue;
				}
				break;
			}
			String[] casesId=timer.getCaseId().split(",");
			PTask autoPTask = new PTask();
			autoPTask.setCaseIds(timer.getCaseId());
			autoPTask.setCreater("定时任务"); 	
			autoPTask.setOpertime(lastDate);
			autoPTask.setTaskname(timer.getName());
			autoPTask.setIp(availableIp.get(0).getIp());
			autoPTask.setEnv(timer.getEnv());
			autoPTask.setBrowser(timer.getBrowser());
			autoPTask.setTaskType("2");
			long pid=pTaskService.add(autoPTask);
			Map<String, String> caseInfo=caseProcessService.getCaseInfo(casesId);//根据caseid解析获取case运行信息
			caseInfo.put("ip", availableIp.get(0).getIp());
			Client client=caseProcessService.socketProcess(caseInfo,(int)pid,"定时任务",timer.getEnv(), timer.getBrowser());
			cls.put(availableIp.get(0).getIp(), client);
			//计算下次运行时间并更新本次运行时间
			timer.setLastexcute(lastDate);
			timer.setNextexcute(nextexcute);
			timer.setLaststatus("成功");
			timerService.update(timer);
			
		} catch (Exception e) {
			timer.setLastexcute(lastDate);
			timer.setNextexcute(nextexcute);
			timer.setLaststatus("失败");
			timerService.update(timer);
		}

	}

}
