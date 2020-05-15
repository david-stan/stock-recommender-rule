package davidstan.sbnz.integration.models;

public class StocksDTO {

	private String personalSector;
	private String educationSector;
	
	private boolean experience;
	private boolean risk;
	
	
	public String getPersonalSector() {
		return personalSector;
	}
	public void setPersonalSector(String personalSector) {
		this.personalSector = personalSector;
	}
	public String getEducationSector() {
		return educationSector;
	}
	public void setEducationSector(String educationSector) {
		this.educationSector = educationSector;
	}
	public boolean isExperience() {
		return experience;
	}
	public void setExperience(boolean experience) {
		this.experience = experience;
	}
	public boolean isRisk() {
		return risk;
	}
	public void setRisk(boolean risk) {
		this.risk = risk;
	}
	
	public StocksDTO() {
		super();
	}
	
	
	
}
