package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Client;
import entity.AutoStep;
import entity.Computer;
import entity.PTask;

public interface CaseProcessService {
	
	
	/**
	 * 
	 * @param caseId
	 * @return Map 需要6个key:ip,folderPath,jarName,{class-?:methods,methods,....},sid
	 */
	public List<Map<String, String>> getCaseList(String[] caseIds);
	/**
	 * 
	 * @param caseId
	 * @return Map 需要2个key:{class:methods,methods,....},sid
	 */
	public Map<String, String> getCaseInfo(String[] caseIds);
	
	/**
	 * socket分发
	 * @param list
	 * @return
	 */
	public Map<String, Object> socketProcess(List<Map<String, String>> list,int pid,String creater,String tskType);
	/**
	 * case动态分发
	 * @param brwTyp TODO
	 * @param list
	 * @return
	 */
	public Client socketProcess(Map<String, String> caseInfo,int pid,String creater,String env, String brwTyp);
	
	/**
	 * 获取机器状态
	 * @param ip
	 * @return
	 */
	public String getPCStatus(String ip);
	/**
	 * 设置机器状态
	 * 
	 * @param ip
	 * @return
	 */
	public void updateStatus(String ip,String computerStatus);
	/**
	 * 设置任务状态
	 * 
	 * @param ip
	 * @return
	 */
	public void updateTaskStatus(String sid,String taskStatus);
	/**
	 * 设置任务终止人
	 * 
	 * @param ip
	 * @return
	 */
	public void updateStopBy(String sid,String stopBy);
	
	/**
	 * 根据caseId获取所需运行的机器IP
	 * @param caseId
	 * @return
	 */

	public Set<String> getIp(String[] caseIds);

	
	/**
	 * 新增自动化任务
	 * @param autoPTask
	 */
	public int addTask(PTask autoPTask);
	

	/**
	 * 保存并返回父任务ID
	 * @param autoPtask
	 * @return
	 */
	public int savePtask(PTask autoPtask);
	/**
	 * 通过jenkinsapi接口获取jenkins在测试机的运行状态
	 * @param ip
	 * @return
	 * @throws IOException 
	 */
	public int getJenkinsStatus(String ip) throws IOException;
	/**
	 * 获取测试机为指定状态的机器ip
	 * @param status 状态
	 * @return
	 */
	public List<Computer> getIpToRun(String ...status);
	/**
	 * 检查分发
	 * @param info
	 * @param ip
	 */
	public Client checkProcess(String info, String ip);
	
	/**
	 * 保存自动化用例步骤时，先删除已有步骤，再重新保存
	 * @param list
	 * @param caseId TODO
	 * @param lastModifier TODO
	 * @throws Exception
	 */
	public void updateAutoSteps(List<AutoStep> list, int caseId, String lastModifier) throws Exception ;

	/**
	 * 生成自动化用例步骤
	 * @param list
	 * @param caseId 用例Id
	 * @param caseTitle 用例标题
	 * @param caseModule 用例所属模块
	 * @return
	 */
	public String generateMethod(List<AutoStep> list, int caseId, String caseTitle, String caseModule);
    
	/**
	 * 根据caseID更新MethodSrc
	 * @param MethodStr
	 * @param caseId
	 */
	public void updateMethodByCaseId(String methodStr,int caseId);
	
	/**
	 * 远程机执行：svn更新代码或更新代码并且执行
	 * @param methodSrc 生成的代码串
	 * @param operate 操作
	 * @param caseId TODO
	 */
	public Client updateProcess(int pid,String ip,String creater,String methodSrc,String operate, String caseId,String type,String env,String brwType);
	/**
	 * 获取case是新增还是修改
	 * true:修改
	 * false:新增
	 */
	public String getCaseAddorModify(String caseId);
}	
