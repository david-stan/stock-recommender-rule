package davidstan.sbnz.integration;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davidstan.sbnz.integration.facts.Risk;
import davidstan.sbnz.integration.facts.Sectors;
import davidstan.sbnz.integration.facts.Stocks;
import davidstan.sbnz.integration.models.Profiles;
import davidstan.sbnz.integration.models.RiskAssessmentDTO;
import davidstan.sbnz.integration.models.Sector;
import davidstan.sbnz.integration.models.StocksDTO;

@Service
public class StockService {

	private static Logger log = LoggerFactory.getLogger(StockService.class);

	private final KieContainer kieContainer;

	@Autowired
	public StockService(KieContainer kieContainer) {
		log.info("Initializing a new example session.");
		this.kieContainer = kieContainer;
	}

	public Risk getStocks(RiskAssessmentDTO[] data, StocksDTO dto) {
		KieSession kieSession = kieContainer.newKieSession();
		
		Sectors sector_financial = new Sectors(Sector.FINANCIALS, data[0].getStd(), data[0].getVolume(), data[0].getClose());
		sector_financial.getAttributes().add(Profiles.RISKY);
		sector_financial.getAttributes().add(Profiles.ECONOMY_SCHOOL);
		sector_financial.getAttributes().add(Profiles.EXPERIENCE);
		sector_financial.getAttributes().add(Profiles.FUNDS);
		sector_financial.getAttributes().add(Profiles.WORK_BANK);
		
		Sectors sector_informatics = new Sectors(Sector.INFORMATION_TECHNOLOGY, data[1].getStd(), data[1].getVolume(), data[1].getClose());
		sector_informatics.getAttributes().add(Profiles.RISKY);
		sector_informatics.getAttributes().add(Profiles.ECONOMY_SCHOOL);
		sector_informatics.getAttributes().add(Profiles.EXPERIENCE);
		sector_informatics.getAttributes().add(Profiles.FUNDS);
		sector_informatics.getAttributes().add(Profiles.INTEREST_TECHNOLOGY);
		
		Sectors sector_communication = new Sectors(Sector.COMMUNICATION_SERVICES, data[2].getStd(), data[2].getVolume(), data[2].getClose());
		sector_communication.getAttributes().add(Profiles.RISKY);
		sector_communication.getAttributes().add(Profiles.ECONOMY_SCHOOL);
		sector_communication.getAttributes().add(Profiles.EXPERIENCE);
		sector_communication.getAttributes().add(Profiles.FUNDS);
		sector_communication.getAttributes().add(Profiles.INTEREST_TECHNOLOGY);
		
		Sectors sector_health = new Sectors(Sector.HEALTH_CARE, data[3].getStd(), data[3].getVolume(), data[3].getClose());
		sector_health.getAttributes().add(Profiles.RISKY);
		sector_health.getAttributes().add(Profiles.ECONOMY_SCHOOL);
		sector_health.getAttributes().add(Profiles.EXPERIENCE);
		sector_health.getAttributes().add(Profiles.FUNDS);
		sector_health.getAttributes().add(Profiles.MEDICAL_WORKER);
		
		Stocks stocks = new Stocks(dto);
		
		kieSession.insert(sector_financial);
		kieSession.insert(sector_informatics);
		kieSession.insert(sector_communication);
		kieSession.insert(sector_health);
		
		kieSession.insert(stocks);
		
		kieSession.setGlobal("externalService", new ExternalService());
		kieSession.getAgenda().getAgendaGroup("profiling").setFocus();
		kieSession.fireAllRules();
		
		Risk risk = null;
		QueryResults results = kieSession.getQueryResults("getRiskObject");
		  for (QueryResultsRow row : results) {
		   risk = (Risk) row.get("$insertedRiskObjectInDRLFile");
		  }
		
		kieSession.dispose();
		return risk;
	}
}
