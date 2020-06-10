package davidstan.sbnz.integration.facts;

import java.util.ArrayList;

import davidstan.sbnz.integration.models.RiskDataDTO;
import davidstan.sbnz.integration.models.Sector;

public class Risk {

	
	private Sector sector;
	private boolean valid;
	private boolean risk;
	private boolean experience;
	private RiskDataDTO[] list;
	private ArrayList<RiskDataDTO> riskFreeStocks;
	
	private int stockIndex;
	
	public Risk(boolean valid, boolean risk, boolean experience, RiskDataDTO[] list, Sector sector) {
		this.sector = sector;
		this.valid = valid;
		this.risk = risk;
		this.experience = experience;
		
		this.list = list;
		this.riskFreeStocks = new ArrayList<>();
		this.stockIndex = 0;
	}
	
	public void iterateStocks(double threshold) {
		if (Double.parseDouble(list[stockIndex].getStd()) <= threshold) {
			riskFreeStocks.add(list[stockIndex]);
		}
	}
	
	public void incrementIndex() {
		stockIndex++;
		if (stockIndex == list.length) {
			valid = false;
		}
	}	
	
	public ArrayList<RiskDataDTO> getRiskFreeStocks() {
		return riskFreeStocks;
	}

	public void setRiskFreeStocks(ArrayList<RiskDataDTO> riskFreeStocks) {
		this.riskFreeStocks = riskFreeStocks;
	}

	public int getStockIndex() {
		return stockIndex;
	}

	public void setStockIndex(int stockIndex) {
		this.stockIndex = stockIndex;
	}

	public RiskDataDTO[] getList() {
		return list;
	}


	public void setList(RiskDataDTO[] list) {
		this.list = list;
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

	

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
