package davidstan.sbnz.integration.facts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import davidstan.sbnz.integration.models.Sector;
import davidstan.sbnz.integration.models.StocksDTO;

public class Stocks implements Serializable {

	
	private double risk;
	private Sector sector;
	
	private boolean experience;
	private boolean riskViable;
	
	private String personalSector;
	private String riskSector;
	private String volumeSector;
	private String closingSector;
	
	private Map<String, Integer> sectorMapping;

	public Stocks(StocksDTO stocksDTO) {
		this.risk = 1;
		personalSector = "INFORMATION_TECHNOLOGY";
		this.sector = Sector.NA;
		
		sectorMapping = new HashMap<>();
	}
	
	public void updateMap(String key) {
		int count = sectorMapping.containsKey(key) ? sectorMapping.get(key) : 0;
		sectorMapping.put(key, count + 1);
	}
	
	public void chooseSector() {
		String sector = sectorMapping.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		this.sector = Sector.valueOf(sector);
	}
	
	

	public String getPersonalSector() {
		return personalSector;
	}

	public void setPersonalSector(String personalSector) {
		this.personalSector = personalSector;
	}

	public String getRiskSector() {
		return riskSector;
	}

	public void setRiskSector(String riskSector) {
		this.riskSector = riskSector;
	}

	public String getVolumeSector() {
		return volumeSector;
	}

	public void setVolumeSector(String volumeSector) {
		this.volumeSector = volumeSector;
	}

	public String getClosingSector() {
		return closingSector;
	}

	public void setClosingSector(String closingSector) {
		this.closingSector = closingSector;
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
