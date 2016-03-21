package de.uniba.dsg.sonar.portability.batch;

import java.util.HashMap;
import java.util.List;

import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.ResourceUtils;

import de.uniba.dsg.sonar.portability.PortabilityMetrics;
import de.uniba.dsg.sonar.portability.utility.DecoratorUtility;

public class PortabilityDecorator implements Decorator {

    private DecoratorUtility utility = new DecoratorUtility();

    private int fileCount = 0;

    private HashMap<Double, Integer> valueCountNumberOfElements = new HashMap<>();
    private HashMap<Double, Integer> valueCountBasicPortability = new HashMap<>();
    private HashMap<Double, Integer> valueCountWeightedPortability = new HashMap<>();
    private HashMap<Double, Integer> valueCountActivityPortability = new HashMap<>();
    private HashMap<Double, Integer> valueCountServiceCommunication = new HashMap<>();
    private HashMap<String, Integer> classificationCount = new HashMap<>();

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        return true;
    }

    @Override
    public void decorate(Resource resource, DecoratorContext context) {
        // This method is executed on the whole tree of resources.
        // Bottom-up navigation : Files -> Dirs -> modules -> project
        if (ResourceUtils.isDirectory(resource)) {

            int fileCountDir = 0;

            HashMap<Double, Integer> valueCountDirNumberOfElements = new HashMap<>();
            HashMap<Double, Integer> valueCountDirBasicPortability = new HashMap<>();
            HashMap<Double, Integer> valueCountDirWeightedPortability = new HashMap<>();
            HashMap<Double, Integer> valueCountDirActivityPortability = new HashMap<>();
            HashMap<Double, Integer> valueCountDirServiceCommunication = new HashMap<>();
            HashMap<String, Integer> dirClassificationCount = new HashMap<>();

            List<DecoratorContext> allChildrencontext = context.getChildren();
            for (DecoratorContext currentContext : allChildrencontext) {
                if (ResourceUtils.isFile(currentContext.getResource())
                        && currentContext.getResource().getLongName().endsWith(".bpel")) {
                    fileCount++;
                    fileCountDir++;

                    @SuppressWarnings("rawtypes")
                    Measure measure = currentContext.getMeasure(PortabilityMetrics.N);
                    int workingIntValue = measure.getIntValue();
                    utility.collectProjectData(valueCountNumberOfElements, workingIntValue);
                    utility.collectDirData(valueCountDirNumberOfElements, workingIntValue);

                    measure = currentContext.getMeasure(PortabilityMetrics.MB);
                    double workingDoubleValue = measure.getValue();
                    utility.collectProjectData(valueCountBasicPortability, workingDoubleValue);
                    utility.collectDirData(valueCountDirBasicPortability, workingDoubleValue);

                    measure = currentContext.getMeasure(PortabilityMetrics.ME);
                    workingDoubleValue = measure.getValue();
                    utility.collectProjectData(valueCountWeightedPortability, workingDoubleValue);
                    utility.collectDirData(valueCountDirWeightedPortability, workingDoubleValue);

                    measure = currentContext.getMeasure(PortabilityMetrics.MA);
                    workingDoubleValue = measure.getValue();
                    utility.collectProjectData(valueCountActivityPortability, workingDoubleValue);
                    utility.collectDirData(valueCountDirActivityPortability, workingDoubleValue);

                    measure = currentContext.getMeasure(PortabilityMetrics.MS);
                    workingDoubleValue = measure.getValue();
                    utility.collectProjectData(valueCountServiceCommunication, workingDoubleValue);
                    utility.collectDirData(valueCountDirServiceCommunication, workingDoubleValue);

                    measure = currentContext.getMeasure(PortabilityMetrics.CLASSIFICATION);
                    String workingStringValue = measure.getData();
                    if (!classificationCount.containsKey(workingStringValue)) {
                        classificationCount.put(workingStringValue, 1);
                    } else {
                        int currentCount = classificationCount.get(workingStringValue);
                        classificationCount.put(workingStringValue, currentCount + 1);
                    }
                    if (!dirClassificationCount.containsKey(workingStringValue)) {
                        dirClassificationCount.put(workingStringValue, 1);
                    } else {
                        int currentCount = dirClassificationCount.get(workingStringValue);
                        dirClassificationCount.put(workingStringValue, currentCount + 1);
                    }
                }
            }
            if (fileCountDir > 0) {
                // Average
                context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_CLASSIFICATION,
                        utility.calculateAverageClassification(dirClassificationCount)));
                context.saveMeasure(PortabilityMetrics.AVG_N, utility.calculateAvgValue(valueCountDirNumberOfElements));
                context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_MB,
                        utility.calculateAvgValue(valueCountDirBasicPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_ME,
                        utility.calculateAvgValue(valueCountDirWeightedPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_MA,
                        utility.calculateAvgValue(valueCountDirActivityPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_MS,
                        utility.calculateAvgValue(valueCountDirServiceCommunication), 2));

                // Lowest
                context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_CLASSIFICATION,
                        utility.calculateLowestClassification(dirClassificationCount)));
                context.saveMeasure(PortabilityMetrics.MIN_N,
                        utility.calculateLowestValue(valueCountDirNumberOfElements));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MB,
                        utility.calculateLowestValue(valueCountDirBasicPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_ME,
                        utility.calculateLowestValue(valueCountDirWeightedPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MA,
                        utility.calculateLowestValue(valueCountDirActivityPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MS,
                        utility.calculateLowestValue(valueCountDirServiceCommunication), 2));

                // Highest
                context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_CLASSIFICATION,
                        utility.calculateHighestClassification(dirClassificationCount)));
                context.saveMeasure(PortabilityMetrics.MAX_N,
                        utility.calculateHighestValue(valueCountDirNumberOfElements));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MB,
                        utility.calculateHighestValue(valueCountDirBasicPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_ME,
                        utility.calculateHighestValue(valueCountDirWeightedPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MA,
                        utility.calculateHighestValue(valueCountDirActivityPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MS,
                        utility.calculateHighestValue(valueCountDirServiceCommunication), 2));

                // Median
                context.saveMeasure(new Measure<>(PortabilityMetrics.MED_CLASSIFICATION,
                        utility.calculateClassificationMedian(dirClassificationCount)));
                context.saveMeasure(PortabilityMetrics.MED_N, utility.calculateMedian(valueCountDirNumberOfElements));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MED_MB,
                        utility.calculateMedian(valueCountDirBasicPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MED_ME,
                        utility.calculateMedian(valueCountDirWeightedPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MED_MA,
                        utility.calculateMedian(valueCountDirActivityPortability), 2));
                context.saveMeasure(new Measure<>(PortabilityMetrics.MED_MS,
                        utility.calculateMedian(valueCountDirServiceCommunication), 2));
            }
        }
        if (ResourceUtils.isProject(resource) && fileCount > 0) {
            // Average
            context.saveMeasure(new Measure<>(PortabilityMetrics.AVG_CLASSIFICATION,
                    utility.calculateAverageClassification(classificationCount)));
            double avgNumberOfElements = utility.calculateAvgValue(valueCountNumberOfElements);
            context.saveMeasure(PortabilityMetrics.AVG_N, avgNumberOfElements);
            double avgBasicPortability = utility.calculateAvgValue(valueCountBasicPortability);
            context.saveMeasure(new Measure<Double>(PortabilityMetrics.AVG_MB, avgBasicPortability, 2));
            double avgWeightedPortability = utility.calculateAvgValue(valueCountWeightedPortability);
            context.saveMeasure(new Measure<Double>(PortabilityMetrics.AVG_ME, avgWeightedPortability, 2));
            double avgActivityPortability = utility.calculateAvgValue(valueCountActivityPortability);
            context.saveMeasure(new Measure<Double>(PortabilityMetrics.AVG_MA, avgActivityPortability, 2));
            double avgServiceCommunication = utility.calculateAvgValue(valueCountServiceCommunication);
            context.saveMeasure(new Measure<Double>(PortabilityMetrics.AVG_MS, avgServiceCommunication, 2));

            // Lowest
            context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_CLASSIFICATION,
                    utility.calculateLowestClassification(classificationCount)));
            context.saveMeasure(PortabilityMetrics.MIN_N, utility.calculateLowestValue(valueCountNumberOfElements));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MB,
                    utility.calculateLowestValue(valueCountBasicPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_ME,
                    utility.calculateLowestValue(valueCountWeightedPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MA,
                    utility.calculateLowestValue(valueCountActivityPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MIN_MS,
                    utility.calculateLowestValue(valueCountServiceCommunication), 2));

            // Highest
            context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_CLASSIFICATION,
                    utility.calculateHighestClassification(classificationCount)));
            context.saveMeasure(PortabilityMetrics.MAX_N, utility.calculateHighestValue(valueCountNumberOfElements));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MB,
                    utility.calculateHighestValue(valueCountBasicPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_ME,
                    utility.calculateHighestValue(valueCountWeightedPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MA,
                    utility.calculateHighestValue(valueCountActivityPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MAX_MS,
                    utility.calculateHighestValue(valueCountServiceCommunication), 2));

            // Median
            context.saveMeasure(new Measure<>(PortabilityMetrics.MED_CLASSIFICATION,
                    utility.calculateClassificationMedian(classificationCount)));
            context.saveMeasure(PortabilityMetrics.MED_N, utility.calculateMedian(valueCountNumberOfElements));
            context.saveMeasure(
                    new Measure<>(PortabilityMetrics.MED_MB, utility.calculateMedian(valueCountBasicPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MED_ME,
                    utility.calculateMedian(valueCountWeightedPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MED_MA,
                    utility.calculateMedian(valueCountActivityPortability), 2));
            context.saveMeasure(new Measure<>(PortabilityMetrics.MED_MS,
                    utility.calculateMedian(valueCountServiceCommunication), 2));
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
