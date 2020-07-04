package davidstan.sbnz.integration.facts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import davidstan.sbnz.integration.models.*;

public class Risk {

	
	private Sector sector;
	private boolean valid;
	private RiskDataDTO[] list;
	private ArrayList<RiskDataDTO> riskFreeStocks;
	private List<Profiles> riskAssessments;
	private double threshold;
	
	private int stockIndex;
	
	private double score;
	private RiskGroup riskGroup;
	
	public Risk(boolean valid, double score, List<Profiles> riskAssessment, RiskDataDTO[] list, Sector sector) {
		this.sector = sector;
		this.valid = valid;
		this.score = score;
		this.riskAssessments = new LinkedList<>();
		this.riskAssessments.addAll(riskAssessment);
		this.riskGroup = RiskGroup.NA;
		this.threshold = 0;
		
		this.list = list;
		this.riskFreeStocks = new ArrayList<>();
		this.stockIndex = 0;
	}
	
	public void processStocks() {
		for (RiskDataDTO data : list) {
			if (Double.parseDouble(data.getStd()) <= this.threshold) {
				riskFreeStocks.add(data);
			}
		}
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public RiskGroup getRiskGroup() {
		return riskGroup;
	}

	public void setRiskGroup(RiskGroup riskGroup) {
		this.riskGroup = riskGroup;
	}

	public List<Profiles> getRiskAssessments() {
		return riskAssessments;
	}

	public void setRiskAssessments(List<Profiles> riskAssessments) {
		this.riskAssessments = riskAssessments;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
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
