package davidstan.sbnz.integration.facts;

import java.io.Serializable;
import java.util.Objects;

import davidstan.sbnz.integration.models.Sector;
import davidstan.sbnz.integration.models.StocksDTO;

public class Stocks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double risk;
	private Sector sector;
	
	private boolean experience;
	private boolean riskViable;

	public Stocks(StocksDTO stocksDTO) {
		this.risk = 1;
		this.experience = stocksDTO.isExperience();
		this.riskViable = stocksDTO.isRisk();
		this.sector = Sector.valueOf(stocksDTO.getSector());
	}
	
	

	public boolean isExperience() {
		return experience;
	}



	public void setExperience(boolean experience) {
		this.experience = experience;
	}



	public boolean isRiskViable() {
		return riskViable;
	}



	public void setRiskViable(boolean riskViable) {
		this.riskViable = riskViable;
	}



	public double getRisk() {
		return risk;
	}
	public void setRisk(double risk) {
		this.risk = risk;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
}
