package de.uniba.dsg.sonar.portability.batch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issue;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.rule.RuleKey;

import bpp.domain.Warning;
import de.uniba.dsg.sonar.portability.PortabilityMetrics;
import prope.metrics.portability.PortabilityAnalyzer;
import prope.reporting.ReportEntry;

public class PortabilitySensor implements Sensor {

    private static final Logger LOG = LoggerFactory.getLogger(PortabilitySensor.class);

    private FileSystem fileSystem;
    private final ResourcePerspectives perspectives;

    /**
     * Use of IoC to get ResourcePerspective and FileSystem
     */
    public PortabilitySensor(FileSystem fileSystem, ResourcePerspectives perspectives) {
        this.fileSystem = fileSystem;
        this.perspectives = perspectives;
    }

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        // This sensor is executed only when there are bpel files
        return fileSystem.hasFiles(fileSystem.predicates().hasLanguage("bpel"));
    }

    @Override
    public void analyse(Project project, SensorContext sensorContext) {
        LOG.info("Starting analyse of Project " + fileSystem.baseDir().getName());
        for (InputFile inputFile : fileSystem.inputFiles(fileSystem.predicates().hasLanguage("bpel"))) {

            PortabilityAnalyzer analyzer = new PortabilityAnalyzer();
            Path currentFilePath = Paths.get(inputFile.absolutePath());
            List<ReportEntry> entries = analyzer.analyzeFile(currentFilePath);

            if (entries.size() > 0) {
                for (ReportEntry currentEntry : entries) {
                    for (String currentMetric : currentEntry.getVariableNames())
                        if (currentMetric.equals("class")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<String>(PortabilityMetrics.CLASSIFICATION, currentEntry.getVariableValue(currentMetric)));
                        } else if (currentMetric.equals("N")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<Integer>(PortabilityMetrics.N, (double) Integer.valueOf(currentEntry.getVariableValue(currentMetric))));
                        } else if (currentMetric.equals("Mb")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<Double>(PortabilityMetrics.MB, Double.valueOf(currentEntry.getVariableValue(currentMetric)), 2));
                        } else if (currentMetric.equals("Me")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<Double>(PortabilityMetrics.ME, Double.valueOf(currentEntry.getVariableValue(currentMetric)), 2));
                        } else if (currentMetric.equals("Ma")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<Double>(PortabilityMetrics.MA, Double.valueOf(currentEntry.getVariableValue(currentMetric)), 2));
                        } else if (currentMetric.equals("Ms")) {
                            sensorContext.saveMeasure(inputFile,
                                    new Measure<Double>(PortabilityMetrics.MS, Double.valueOf(currentEntry.getVariableValue(currentMetric)), 2));
                        }
                }
            }

            List<Warning> warnings = analyzer.getWarningsFromLastAnalysis();
            if (warnings.size() > 0) {
                Issuable issuable = perspectives.as(Issuable.class, inputFile);
                for (Warning warning : warnings) {
                    if (issuable != null) {
                        // can be used
                        Issue issue = issuable.newIssueBuilder()
                                .ruleKey(RuleKey.of("portability-issues", warning.getAssertion().getId()))
                                .line(warning.getLine())
                                .message(warning.getAssertion().getDiagnosticMessage())
                                .build();
                        issuable.addIssue(issue);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
