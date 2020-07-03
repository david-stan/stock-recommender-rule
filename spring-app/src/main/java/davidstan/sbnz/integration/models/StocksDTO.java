package davidstan.sbnz.integration.models;

public class StocksDTO {

	private boolean experience;
	private boolean risk;
	private boolean economy;
	private boolean funds;
	private boolean bank;
	private boolean technology;
	private boolean medical;
	
	
	public StocksDTO() {
		super();
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


	public boolean isEconomy() {
		return economy;
	}


	public void setEconomy(boolean economy) {
		this.economy = economy;
	}


	public boolean isFunds() {
		return funds;
	}


	public void setFunds(boolean funds) {
		this.funds = funds;
	}


	public boolean isBank() {
		return bank;
	}


	public void setBank(boolean bank) {
		this.bank = bank;
	}


	public boolean isTechnology() {
		return technology;
	}


	public void setTechnology(boolean technology) {
		this.technology = technology;
	}


	public boolean isMedical() {
		return medical;
	}


	public void setMedical(boolean medical) {
		this.medical = medical;
	}
	
	
	
}
