package dao;

import java.util.List;

import entity.AutoStep;
import entity.Case;
import entity.Computer;
import entity.PTask;
import entity.STask;

public interface PlatFormDao 
{
	/**
	 * 返回插入的任务id
	 * 保存父任务
	 * @param PTask
	 */
	public int savePTask(PTask PTask);
	
	/**
	 * 返回插入的任务id
	 * 保存子任务
	 * @param STask
	 */
	public int saveSTask(STask STask);
	
	/**
	 * 根据ip查询具体机器的状态
	 * @param ip
	 * @return
	 */
	public Computer getcomputer(String ip);
	
//	/**
//	 * 获取父任务列表
//	 * @return
//	 */
//	public List<PTask> getPTasks();
//	
//	/**
//	 * 获取子任务列表
//	 * @return
//	 */
//	public List<STask> getSTasks();
	
	/**
	 * 更新机器的状态
	 * @param ip 
	 * @param computerStatus
	 */
	public void updateComputerStatus(String ip,String computerStatus);
	/**
	 * 更新任务的状态
	 * @param ip
	 * @param taskStatus
	 */
	public void updateTaskStatus(String sid, String taskStatus);
	/**
	 * 更新终止人
	 * @param ip 
	 * @param computerStatus
	 */
	public void updateTaskStopBy(String sid,String stopBy);
	
	/**
	 * 根据id查询case信息
	 * @param ids
	 * @return
	 */
	public List<Case> findcasesByIds(String[] ids);
	/**
	 * 根据指定状态查机器ip
	 * @param ids
	 * @return
	 */
	public List<Computer> findIpByStatus(String ...status);
	

	/**
	 * 批量保存步骤信息
	 * @param list 步骤信息列表
	 * @param caseId TODO
	 * @param lastModifier TODO
	 * @throws Exception 
	 */
	public void saveStep(List<AutoStep> list) throws Exception;

	/**
	 * 根据caseID删除步骤信息
	 */
	public void deleteStep(int caseId);
	
	public void updateMethodByCaseId(String methodSrc,int caseId);
	
	/**
	 * 根据methodsrc
	 * @param ip
	 * @return
	 */
	public String getMethodSrc(String caseId);
	
}
