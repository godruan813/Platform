package serviceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import service.CaseProcessService;
import utils.Client;
import utils.Constants;
import utils.HttpRequestProxy;
import dao.PlatFormDao;
import entity.AutoStep;
import entity.Case;
import entity.Computer;
import entity.PTask;
import entity.STask;


public class CaseProcessServiceImpl implements CaseProcessService {

	@Autowired
	private PlatFormDao platFormDao;
	@Autowired
	private HttpRequestProxy httpClient;

	public List<Map<String, String>> getCaseList(String[] caseIds) {
		List<Map<String, String>> caseInfo = new ArrayList<Map<String, String>>();
		List<Case> cases = platFormDao.findcasesByIds(caseIds);
		for (Case case1 : cases) {
			boolean flag = true;
			for (Map<String, String> map : caseInfo) {
				if (map.get("ip").equals(case1.getIp())) {
					flag = false;
					break;
				}
			}
			// list中没有这台机器，则新加一个map
			if (flag) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				String ip = case1.getIp();
				map.put("ip", ip);
				String className = case1.getClassName();
				map.put("class-" + className, case1.getMethodName());
				caseInfo.add(map);
			}
			// map中已经有此台机器，需要在再次判断cae的类名，如果是同一台机器的相同类名，则不做处理，如果是不同的类名，则需要以map.put("class-"
			// + case1.getClassName(),
			// platFormDao.findMethodNamesByClassName(case1.getClassName()));的形式
			else {
				a: for (Map<String, String> map : caseInfo) {
					if (map.get("ip").equals(case1.getIp())) {
						boolean classFlag = false;
						for (Entry<String, String> e : map.entrySet()) {
							String key = e.getKey();
							String className;
							if (key.contains("class-")) {
								className = key.split("-", 2)[1];
								if (className.equals(case1.getClassName())) {// 有相同的class已经存在
									classFlag = true;
									map.put(key,
											e.getValue() + ","
													+ case1.getMethodName());
									break a;
								}
							} else {
								continue;
							}
						}
						if (!classFlag) {
							map.put("class-" + case1.getClassName(),
									case1.getMethodName());
							break a;
						}
					}
				}
			}
		}
		return caseInfo;
	}

	public Map<String, String> getCaseInfo(String[] caseIds) {
		Map<String, String> caseInfo = new HashMap<String, String>();
		List<Case> cases = platFormDao.findcasesByIds(caseIds);
		for (Case c : cases) {
			String className = c.getClassName();
			String methodName = c.getMethodName();
			if (caseInfo.containsKey("class-"+className)) {
				String method = caseInfo.get("class-"+className);
				caseInfo.put("class-"+className, method + "," + methodName);
			} else {
				caseInfo.put("class-"+className, methodName);
			}
		}
		return caseInfo;
	}

	public Client socketProcess(Map<String, String> caseInfo,
			int pid, String creater, String env, String brwTyp) {
		Client c = new Client();
		try {
			//client.put("errorMessage", "");
			//client.put("errorIp", "");
			String ip = caseInfo.get("ip");
			c.setIp(ip);
			Map<String, String> commandMap = caseInfo;
			STask task = new STask();
			task.setCreater(creater);
			task.setStartTime(new Date());
			//task.setEndTime(new Date());
			task.setIp(ip);
			task.setPid(pid);
			int sid = platFormDao.saveSTask(task);
			commandMap.put("sid", String.valueOf(sid));
			commandMap.put("env", env);
			commandMap.put("operateType", Constants.OPERATE_EXCUTE);
			commandMap.put("brwTyp", brwTyp);
			String commandJson = JSONObject.fromObject(commandMap).toString();
			c.connect(ip, Constants.DEFAULT_SOCKET_PORT);
			c.send(commandJson);
			c.setSid(String.valueOf(sid));
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			//c.setErrorIp(e.getMessage());
			c.setErrorMessage(c.getIp() + "Socket连接异常,请联系管理员");
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			c.setErrorMessage("运行异常,请联系管理员!");
			return c;
		}
	}

	public String getPCStatus(String ip) {
		return platFormDao.getcomputer(ip).getComputerStatus();
	}

	public Set<String> getIp(String[] caseIds) {
		List<Case> cases = platFormDao.findcasesByIds(caseIds);
		Set<String> ips = new HashSet<String>();
		for (Case case1 : cases) {
			ips.add(case1.getIp());
		}
		return ips;
	}

	public void updateStatus(String ip, String computerStatus) {
		platFormDao.updateComputerStatus(ip, computerStatus);
	}
	
	public void updateTaskStatus(String sid, String taskStatus) {
		platFormDao.updateTaskStatus(sid, taskStatus);
	}

	public int savePtask(PTask autoPtask) {
		return platFormDao.savePTask(autoPtask);
	}

	public int getJenkinsStatus(String ip) throws IOException {
		String url = Constants.JENKINS_URL + "/label/" + ip
				+ "/api/json?pretty=true";
		String json = httpClient.doRequest(url, null, null, "utf-8");
		JSONObject obj = JSONObject.fromObject(json);
		return Integer.parseInt(obj.get("busyExecutors").toString());
	}

	public int addTask(PTask pTask) {
		return platFormDao.savePTask(pTask);
	}

	public List<Computer> getIpToRun(String... status) {
		return platFormDao.findIpByStatus(status);
	}

	public void updateStopBy(String sid, String stopBy) {
		platFormDao.updateTaskStopBy(sid, stopBy);
	}
	
	public Client checkProcess(String info,String ip){
		
		Client c=new Client();
		try {
			c.connect(ip, Constants.DEFAULT_SOCKET_PORT);
			String receive=c.send(info);
			c.setCheckInfo(receive);
			return c;
		} catch (IOException e) {
			c.setErrorMessage(c.getIp() + "Socket连接异常,请联系管理员");
			return c;
		} catch (Exception e) {
			c.setErrorMessage("运行异常,请联系管理员!");
			return c;
		}
		
	}

	public void updateMethodByCaseId(String methodSrc, int caseId) {
		platFormDao.updateMethodByCaseId(methodSrc, caseId);
	}

	public String getCaseAddorModify(String caseId) {
		String methodSrc=platFormDao.getMethodSrc(caseId);
		return StringUtils.isEmpty(methodSrc)?"add":"modify";
	}
	
	public Client updateProcess(int pid,String ip,String creater,String methodSrc, String operate, String caseId,String type,String env,String brwTyp) {
		Client c = new Client();
		try {
			Map<String, String> commandMap = new HashMap<String, String>();
			PTask ptask = new PTask();
			ptask.setId(pid);
			STask task = new STask();
			task.setCreater(creater);
			task.setStartTime(new Date());
			task.setIp(ip);
			int sid = platFormDao.saveSTask(task);
			commandMap.put("sid", String.valueOf(sid));
			commandMap.put("env", env);
			commandMap.put("operateType", Constants.OPERATE_AUTO);
			commandMap.put("operate", operate);
			commandMap.put("content", methodSrc);
			commandMap.put("type", type);
			commandMap.put("caseId", caseId);
			commandMap.put("brwTyp", brwTyp);
			String commandJson = JSONObject.fromObject(commandMap).toString();
			c.connect(ip, Constants.DEFAULT_SOCKET_PORT);
			c.send(commandJson);
			c.setSid(String.valueOf(sid));
			c.setCheckInfo("执行开始");
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			//c.setErrorIp(e.getMessage());
			c.setErrorMessage(c.getIp() + "Socket连接异常,请联系管理员");
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			c.setErrorMessage("运行异常,请联系管理员!");
			return c;
		}
		
	}


	public String generateMethod(List<AutoStep> list, int caseId,
			String caseTitle, String caseModule) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateAutoSteps(List<AutoStep> list, int caseId, String lastModifier) throws Exception {
		//platFormDao.deleteStep(caseId);
		//platFormDao.saveStep(list, caseId, lastModifier);
	}

	public Map<String, Object> socketProcess(List<Map<String, String>> list,
			int pid, String creater, String tskType) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
