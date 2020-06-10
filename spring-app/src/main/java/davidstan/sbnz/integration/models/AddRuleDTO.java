package davidstan.sbnz.integration.models;

public class AddRuleDTO {

	private String ruleTitle;
	private String ruleContent;
	
	public AddRuleDTO() {}
	
	public String getRuleTitle() {
		return ruleTitle;
	}
	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}
	public String getRuleContent() {
		return ruleContent;
	}
	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}
	
	
}
