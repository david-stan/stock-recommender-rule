package davidstan.sbnz.config;

import davidstan.sbnz.integration.models.RiskDataDTO;

public class ExternalServiceConfig {

	public static RiskDataDTO[] getRiskData() {
		RiskDataDTO[] data = new RiskDataDTO[] {
				new RiskDataDTO("A", "4.5"),
				new RiskDataDTO("AA", "4"),
				new RiskDataDTO("B", "8"),
				new RiskDataDTO("C", "7"),
				new RiskDataDTO("CC", "4.8"),
				new RiskDataDTO("D", "60"),
				new RiskDataDTO("DB", "1.98"),
				new RiskDataDTO("E", "6"),
				new RiskDataDTO("ERT", "5.7"),
				new RiskDataDTO("AA", "7.7")
		};
		return data;
	}
}
