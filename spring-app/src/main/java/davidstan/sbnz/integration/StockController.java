package davidstan.sbnz.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import davidstan.sbnz.integration.facts.Item;

@RestController
public class StockController {

	private static Logger log = LoggerFactory.getLogger(StockController.class);

	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
	public Item getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
			@RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://localhost:5000/stock/sector";
		ResponseEntity<String[]> response
		  = restTemplate.getForEntity(fooResourceUrl, String[].class);
		
		System.out.println(response.getBody());

		Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);

		log.debug("Item request received for: " + newItem);

		Item i2 = stockService.getClassifiedItem(newItem);

		return i2;
	}
}
