package davidstan.sbnz.integration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import davidstan.sbnz.integration.facts.Risk;
import davidstan.sbnz.integration.facts.Sectors;
import davidstan.sbnz.integration.facts.Stocks;
import davidstan.sbnz.integration.models.AddRuleDTO;
import davidstan.sbnz.integration.models.RiskAssessmentDTO;
import davidstan.sbnz.integration.models.Sector;
import davidstan.sbnz.integration.models.StocksDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@Autowired
	private RuleService ruleService;

	@RequestMapping(value = "/stocks", method = RequestMethod.POST, produces = "application/json")
	public Risk getStocks(@RequestBody StocksDTO stocksDTO) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/sector";
		ResponseEntity<RiskAssessmentDTO[]> response
		  = restTemplate.getForEntity(fooResourceUrl, RiskAssessmentDTO[].class);

		RiskAssessmentDTO[] data = response.getBody();
		
		Risk r = stockService.getStocks(data, stocksDTO);

		return r;
	}
	
	@RequestMapping(value = "/rules", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addRules(@RequestBody AddRuleDTO rules) throws IOException, URISyntaxException {
		String title = ruleService.addRule(rules);
		return new ResponseEntity<>("Rule " + title + " added.", HttpStatus.OK);
	}
}
