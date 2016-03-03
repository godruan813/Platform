package entity;

/**
 * 自动化用例操作步骤实体类
 * @author chenshanshan
 *
 */
public class AutoStep {

	private int id;//步骤ID
	private int caseId;//步骤关联的用例编号
	private String sequence;//步骤顺序
	private String locatorMode;//定位方式
	private String locator;//定位值
	private String operation;//操作
	private String parameter;//参数值
	private String explanation;//步骤说明
	private String lastModifier;//最后操作者
	private String elementInfo;//获取页面中用于断言的信息的操作：如getTitle,getText
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getLocatorMode() {
		return locatorMode;
	}
	public void setLocatorMode(String locatorMode) {
		this.locatorMode = locatorMode;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	public String getElementInfo() {
		return elementInfo;
	}
	public void setElementInfo(String elementInfo) {
		this.elementInfo = elementInfo;
	}
	
	
}
