package davidstan.sbnz.integration.models;

public class RiskDataDTO {

	private String stock;
	private String std;
	
	public RiskDataDTO() {}
	
	public RiskDataDTO(String stock, String std) {
		this.stock = stock;
		this.std = std;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}
	
	
}
