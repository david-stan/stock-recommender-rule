package davidstan.sbnz.integration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


import davidstan.sbnz.integration.models.AddRuleDTO;

@Service
public class RuleService {
	
	public RuleService() {}

	public String addRule(AddRuleDTO rules) throws IOException {
		List<String> text = Arrays.asList(rules.getRuleContent().split("\n"));
		Path file = Paths.get("../spring-kjar/src/main/resources/davidstan/sbnz/integration/" + rules.getRuleTitle() + ".drl");
		Files.write(file, text, StandardCharsets.UTF_8);
		return rules.getRuleTitle();
	}
	
}
