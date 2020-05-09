package davidstan.sbnz.integration;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davidstan.sbnz.integration.facts.Stocks;

@Service
public class StockService {

	private static Logger log = LoggerFactory.getLogger(StockService.class);

	private final KieContainer kieContainer;

	@Autowired
	public StockService(KieContainer kieContainer) {
		log.info("Initializing a new example session.");
		this.kieContainer = kieContainer;
	}

	public Stocks getStocks(Stocks s) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(s);
		kieSession.fireAllRules();
		kieSession.dispose();
		return s;
	}
}
