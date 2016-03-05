package de.uniba.dsg.sonar.portability.rules;

import org.sonar.api.profiles.AnnotationProfileParser;
import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.utils.ValidationMessages;

import de.uniba.dsg.sonar.portability.checks.CheckRepository;
import de.uniba.dsg.sonar.portability.language.Bpel;

public class BpelSonarWayProfile extends ProfileDefinition {

	private final AnnotationProfileParser annotationProfileParser;

	public BpelSonarWayProfile(AnnotationProfileParser annotationProfileParser) {
		this.annotationProfileParser = annotationProfileParser;
	}

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
		return annotationProfileParser.parse(
				CheckRepository.REPOSITORY_KEY,
				CheckRepository.SONAR_WAY_PROFILE_NAME,
				Bpel.KEY,
				CheckRepository.getCheckClasses(),
				validation);
	}

}
