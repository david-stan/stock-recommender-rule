package davidstan.sbnz.integration.facts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import davidstan.sbnz.integration.models.Sector;
import davidstan.sbnz.integration.models.StocksDTO;

public class Stocks implements Serializable {

	private Sector sector;
	
	private String personalSector;
	private String educationSector;
	
	private String riskSector;
	private String volumeSector;
	private String closingSector;
	
	private boolean risk;
	private boolean experience;
	
	private Map<String, Integer> sectorMapping;
	

	public Stocks(StocksDTO stocksDTO) {
		personalSector = stocksDTO.getPersonalSector();
		educationSector = stocksDTO.getEducationSector();
		this.sector = Sector.NA;
		
		risk = stocksDTO.isRisk();
		experience = stocksDTO.isExperience();
		
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

	public String getEducationSector() {
		return educationSector;
	}

	public void setEducationSector(String educationSector) {
		this.educationSector = educationSector;
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

	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
}
