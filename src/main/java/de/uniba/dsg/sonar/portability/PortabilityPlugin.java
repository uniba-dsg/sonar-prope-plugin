package de.uniba.dsg.sonar.portability;

/**
 * This Plugin is oriented on the sonar-xml-plugin and the reference-plugin
 */
import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import de.uniba.dsg.sonar.portability.batch.PortabilityDecorator;
import de.uniba.dsg.sonar.portability.batch.PortabilitySensor;
import de.uniba.dsg.sonar.portability.language.Bpel;
import de.uniba.dsg.sonar.portability.rules.BpelSonarWayProfile;
import de.uniba.dsg.sonar.portability.rules.PortabilityRulesDefinition;
import de.uniba.dsg.sonar.portability.ui.PortabilityDashboard;

@Properties({
		@Property(key = PortabilityPlugin.MY_PROPERTY, name = "Portability Property", description = "This Plugin calculates Issues and Portability Metrics", defaultValue = "Portability Plugin") })
public final class PortabilityPlugin extends SonarPlugin {

	public static final String MY_PROPERTY = "sonar.portability.plugin";
	public static final String FILE_SUFFIXES_KEY = "sonar.bpel.file.suffixes";

	@SuppressWarnings("rawtypes")
	@Override
	public List getExtensions() {
		return Arrays.asList(
				// Definitions
				PortabilityMetrics.class, Bpel.class, BpelSonarWayProfile.class, PortabilityRulesDefinition.class,

		// Batch
				PortabilitySensor.class, PortabilityDecorator.class,

		// UI
				PortabilityDashboard.class);
	}
}
