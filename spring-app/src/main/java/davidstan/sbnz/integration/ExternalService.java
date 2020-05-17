package davidstan.sbnz.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import davidstan.sbnz.integration.models.RiskDataDTO;

@Service
public class ExternalService {

	public ExternalService() {}
	
	public RiskDataDTO[] test(String sector) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/risk/" + sector;
		ResponseEntity<RiskDataDTO[]> response
		  = restTemplate.getForEntity(fooResourceUrl, RiskDataDTO[].class);
		
		return response.getBody();
	}
	
	
	
	
}
