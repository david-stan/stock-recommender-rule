package davidstan.sbnz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import davidstan.sbnz.config.ExternalServiceConfig;
import davidstan.sbnz.integration.ExternalService;
import davidstan.sbnz.integration.facts.Risk;
import davidstan.sbnz.integration.facts.Stocks;
import davidstan.sbnz.integration.models.RiskDataDTO;
import davidstan.sbnz.integration.models.Sector;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = davidstan.sbnz.integration.SpringApp.class)
public class RulesTest {
	
	@Autowired
	private KieContainer kieContainer;
	
	private KieSession kieSession;
	
	@MockBean
	private ExternalService serviceMocked;
	
	private Stocks stocks;
	
	@Before
	public void setUp() {
		this.stocks = new Stocks();
		this.stocks.setClosingSector("HEALTH_CARE");
		//.stocks.setEducationSector("HEALTH_CARE");
		//this.stocks.setPersonalSector("HEALTH_CARE");
		this.stocks.setRiskSector("COMMUNICATION_SERVICES");
		this.stocks.setVolumeSector("COMMUNICATION_SERVICES");
		this.stocks.setRisk(true);
		this.stocks.setExperience(false);
		
		RiskDataDTO[] data = ExternalServiceConfig.getRiskData();
		
		when(serviceMocked.test("HEALTH_CARE")).thenReturn(data);
		
		this.kieSession = kieContainer.newKieSession();
		this.kieSession.insert(this.stocks);
		this.kieSession.setGlobal("externalService", serviceMocked);
		this.kieSession.getAgenda().getAgendaGroup("profiling").setFocus();
	}
	
	@Ignore
	@Test()
	public void whenStocksValid_assertRiskFactNotNull() {
 
		this.kieSession.fireAllRules();
		Risk risk = null;
		QueryResults results = kieSession.getQueryResults("getRiskObject");
		  for (QueryResultsRow row : results) {
		   risk = (Risk) row.get("$insertedRiskObjectInDRLFile");
		  }
		
		this.kieSession.dispose();
		
		assertNotNull(risk);
		assertNotEquals(risk.getRiskFreeStocks().size(), 0);
	}
	
	@Ignore
	@Test
	public void whenStocksValid_assertChosenSectorIsValid() {
 
		this.kieSession.fireAllRules();
		Risk risk = null;
		QueryResults results = kieSession.getQueryResults("getRiskObject");
		  for (QueryResultsRow row : results) {
		   risk = (Risk) row.get("$insertedRiskObjectInDRLFile");
		  }
		
		this.kieSession.dispose();
		
		assertNotNull(risk);
		assertNotEquals(risk.getRiskFreeStocks().size(), 0);
		assertEquals(risk.getSector(), Sector.HEALTH_CARE);
	}
	
}
