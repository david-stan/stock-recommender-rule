package davidstan.sbnz.integration.models;

public class RiskAssessmentDTO {
	
	private double std;
	private double volume;
	private double close;
	
	public RiskAssessmentDTO() { }

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}
	
	

}
