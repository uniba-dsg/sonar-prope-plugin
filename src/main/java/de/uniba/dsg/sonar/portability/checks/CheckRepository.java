package de.uniba.dsg.sonar.portability.checks;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class CheckRepository {

    public static final String REPOSITORY_KEY = "bpel";
    public static final String REPOSITORY_NAME = "SonarQube";
    public static final String SONAR_WAY_PROFILE_NAME = "Sonar way";

    private CheckRepository() {
    }


    @SuppressWarnings("rawtypes")
    public static List<Class> getCheckClasses() {
        ImmutableList.Builder<Class> builder = ImmutableList.builder();
        return builder.build();
    }
}
