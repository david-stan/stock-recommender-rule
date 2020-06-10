package davidstan.sbnz.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import davidstan.sbnz.integration.facts.Risk;
import davidstan.sbnz.integration.facts.Stocks;
import davidstan.sbnz.integration.models.AddRuleDTO;
import davidstan.sbnz.integration.models.StocksDTO;

@RestController
public class StockController {

	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@Autowired
	private RuleService ruleService;

	@RequestMapping(value = "/stocks", method = RequestMethod.GET, produces = "application/json")
	public Risk getQuestions(@RequestBody StocksDTO stocksDTO) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/sector";
		ResponseEntity<String[]> response
		  = restTemplate.getForEntity(fooResourceUrl, String[].class);

		Stocks stocks = new Stocks(stocksDTO);
		stocks.setRiskSector(response.getBody()[0]);
		stocks.setVolumeSector(response.getBody()[1]);
		stocks.setClosingSector(response.getBody()[2]);

		Risk r = stockService.getStocks(stocks);

		return r;
	}
	
	@RequestMapping(value = "/rules", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addRules(@RequestBody AddRuleDTO rules) throws IOException {
		String title = ruleService.addRule(rules);
		return new ResponseEntity<>("Rule " + title + " added.", HttpStatus.OK);
	}
}
