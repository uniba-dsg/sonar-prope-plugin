package de.uniba.dsg.sonar.portability.rules;

import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;

import bpp.domain.PortabilityLevel;
import bpp.domain.assertions.TestAssertion;
import bpp.domain.assertions.TestAssertions;

public final class PortabilityRulesDefinition implements RulesDefinition {

    public PortabilityRulesDefinition() {
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository("portability-issues", "bpel").setName("portability-issues");
        TestAssertions testAssertions = new TestAssertions();
        List<TestAssertion> rules = testAssertions.createAll();
        for (TestAssertion rule : rules) {
            NewRule newRule = repository.createRule(rule.getId())
                    .setName(rule.getId())
                    .setHtmlDescription(rule.getDescription());
            if (rule.getLevel().equals(PortabilityLevel.PORTABLE)) {
                newRule.setSeverity(org.sonar.api.rule.Severity.INFO);
            } else if (rule.getLevel().equals(PortabilityLevel.WIDELY_PORTABLE)) {
                newRule.setSeverity(org.sonar.api.rule.Severity.MINOR);
            } else if (rule.getLevel().equals(PortabilityLevel.PARTIALLY_PORTABLE)) {
                newRule.setSeverity(org.sonar.api.rule.Severity.MAJOR);
            } else if (rule.getLevel().equals(PortabilityLevel.LIMITED_PORTABILITY)) {
                newRule.setSeverity(org.sonar.api.rule.Severity.CRITICAL);
            } else {
                newRule.setSeverity(org.sonar.api.rule.Severity.BLOCKER);
            }
        }
        repository.done();

    }

}
