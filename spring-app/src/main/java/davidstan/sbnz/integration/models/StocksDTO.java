package davidstan.sbnz.integration.models;

public class StocksDTO {

	private String sector;
	private boolean experience;
	private boolean risk;
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
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
