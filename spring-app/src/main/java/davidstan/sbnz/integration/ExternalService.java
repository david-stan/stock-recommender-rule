package davidstan.sbnz.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

	public ExternalService() {}
	
	public String[] test() {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/sector";
		ResponseEntity<String[]> response
		  = restTemplate.getForEntity(fooResourceUrl, String[].class);
		return response.getBody();
	}
	
	
	
	
}
