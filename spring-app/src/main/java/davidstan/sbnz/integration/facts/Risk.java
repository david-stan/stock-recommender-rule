package davidstan.sbnz.integration.facts;

public class Risk {

	private boolean valid;
	private boolean risk;
	private boolean experience;
	
	public Risk(boolean valid, boolean risk, boolean experience) {
		this.valid = valid;
		this.risk = risk;
		this.experience = experience;
	}

	public boolean isRisk() {
		return risk;
	}



	public void setRisk(boolean risk) {
		this.risk = risk;
	}



	public boolean isExperience() {
		return experience;
	}



	public void setExperience(boolean experience) {
		this.experience = experience;
	}



	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
