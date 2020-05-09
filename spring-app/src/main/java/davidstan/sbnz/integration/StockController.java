package davidstan.sbnz.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import davidstan.sbnz.integration.facts.Stocks;
import davidstan.sbnz.integration.models.StocksDTO;

@RestController
public class StockController {

	private static Logger log = LoggerFactory.getLogger(StockController.class);

	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
	public Stocks getQuestions(@RequestBody StocksDTO stocksDTO) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/sector";
		ResponseEntity<String[]> response
		  = restTemplate.getForEntity(fooResourceUrl, String[].class);

		Stocks stocks = new Stocks(stocksDTO);
		stocks.setRiskSector(response.getBody()[0]);
		stocks.setVolumeSector(response.getBody()[1]);
		stocks.setClosingSector(response.getBody()[2]);

		Stocks s2 = stockService.getStocks(stocks);

		return s2;
	}
}
