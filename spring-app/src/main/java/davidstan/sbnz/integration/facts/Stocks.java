package davidstan.sbnz.integration.facts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import davidstan.sbnz.integration.models.*;

public class Stocks implements Serializable {

	private Sector sector;
	
	private String riskSector;
	private String volumeSector;
	private String closingSector;
	
	private boolean risk;
	private boolean experience;
	
	private List<Profiles> chosenProfile;
	
	private Map<String, Integer> sectorMapping;
	
	public Stocks() {
		sectorMapping = new HashMap<>();
	}

	public Stocks(StocksDTO stocksDTO) {
		this.sector = Sector.NA;
		
		risk = stocksDTO.isRisk();
		experience = stocksDTO.isExperience();
		
		sectorMapping = new HashMap<>();
		
		this.chosenProfile = new LinkedList<>();
		if (stocksDTO.isBank()) this.chosenProfile.add(Profiles.WORK_BANK);
		if (stocksDTO.isFunds()) this.chosenProfile.add(Profiles.FUNDS);
		if (stocksDTO.isEconomy()) this.chosenProfile.add(Profiles.ECONOMY_SCHOOL);
		if (stocksDTO.isExperience()) this.chosenProfile.add(Profiles.EXPERIENCE);
		if (stocksDTO.isRisk()) this.chosenProfile.add(Profiles.RISKY);
		if (stocksDTO.isMedical()) this.chosenProfile.add(Profiles.MEDICAL_WORKER);
		if (stocksDTO.isTechnology()) this.chosenProfile.add(Profiles.INTEREST_TECHNOLOGY);
	}
	
	public void updateMap(String key) {
		int count = sectorMapping.containsKey(key) ? sectorMapping.get(key) : 0;
		sectorMapping.put(key, count + 1);
	}
	
	public void chooseSector() {
		String sector = sectorMapping.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		this.sector = Sector.valueOf(sector);
	}
	
	
	
	public List<Profiles> getChosenProfile() {
		return chosenProfile;
	}

	public void setChosenProfile(List<Profiles> chosenProfile) {
		this.chosenProfile = chosenProfile;
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
