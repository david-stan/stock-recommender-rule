package davidstan.sbnz.integration.facts;

import java.util.ArrayList;

import davidstan.sbnz.integration.models.RiskDataDTO;

public class Risk {

	private boolean valid;
	private boolean risk;
	private boolean experience;
	private RiskDataDTO[] list;
	private ArrayList<RiskDataDTO> riskFreeStocks;
	
	private int stockIndex;
	
	public Risk(boolean valid, boolean risk, boolean experience, RiskDataDTO[] list) {
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



	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
