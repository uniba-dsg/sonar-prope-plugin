package de.uniba.dsg.sonar.portability.utility;

import java.util.Arrays;
import java.util.HashMap;

public class DecoratorUtility {

    public void collectDirData(HashMap<Double, Integer> valueCountHashMap, double workingDoubleValue) {
        if (!valueCountHashMap.containsKey(workingDoubleValue)) {
            valueCountHashMap.put(workingDoubleValue, 1);
        } else {
            int currentCount = valueCountHashMap.get(workingDoubleValue);
            valueCountHashMap.put(workingDoubleValue, currentCount + 1);
        }
    }

    public void collectProjectData(HashMap<Double, Integer> valueCountHashMap, double workingDoubleValue) {
        collectDirData(valueCountHashMap, workingDoubleValue);
    }

    public String calculateAverageClassification(HashMap<String, Integer> classificationCount) {
        String avgClassification = "";
        int highestCount = 0;
        for (String key : classificationCount.keySet()) {
            if (classificationCount.get(key) > highestCount) {
                highestCount = classificationCount.get(key);
                avgClassification = key;
            }
        }
        return avgClassification;
    }

    public String calculateLowestClassification(HashMap<String, Integer> classificationCount) {
        if (classificationCount.containsKey("nonportable")) {
            return "nonportable";
        } else if (classificationCount.containsKey("limited portability")) {
            return "limited portability";
        } else if (classificationCount.containsKey("partially portable")) {
            return "partially portable";
        } else if (classificationCount.containsKey("widely portable")) {
            return "widely portable";
        } else {
            return "portable";
        }
    }

    public String calculateHighestClassification(HashMap<String, Integer> classificationCount) {
        if (classificationCount.containsKey("portable")) {
            return "portable";
        } else if (classificationCount.containsKey("widely portable")) {
            return "widely portable";
        } else if (classificationCount.containsKey("partially portable")) {
            return "partially portable";
        } else if (classificationCount.containsKey("limited portability")) {
            return "limited portability";
        } else {
            return "nonportable";
        }
    }

    public double calculateAvgValue(HashMap<Double, Integer> valueHashMap) {
        int fileCount = 0;
        double sumValues = 0;
        for (double currentDouble : valueHashMap.keySet()) {
            int frequency = valueHashMap.get(currentDouble);
            sumValues += (frequency * currentDouble);
            fileCount += frequency;
        }
        return sumValues / fileCount;
    }

    public String calculateClassificationMedian(HashMap<String, Integer> stringValues) {
        String[] classifications = new String[stringValues.size()];
        int i = 0;
        for (String currentClassification : stringValues.keySet()) {
            classifications[i] = currentClassification;
            i++;
        }
        if (classifications.length % 2 == 0) {
            return classifications[classifications.length / 2 - 1];
        } else {
            return classifications[(classifications.length / 2)];
        }
    }

    public double calculateMedian(HashMap<Double, Integer> countValues) {
        double[] ValuesToSort = new double[countValues.size()];
        int i = 0;
        for (double key : countValues.keySet()) {
            ValuesToSort[i] = key;
            i++;
        }
        Arrays.sort(ValuesToSort);
        if (ValuesToSort.length % 2 == 0) {
            return ValuesToSort[ValuesToSort.length / 2 - 1];
        } else {
            return ValuesToSort[(ValuesToSort.length / 2)];
        }
    }

    public double calculateLowestValue(HashMap<Double, Integer> valueHashMap) {
        double currentLow = Double.MAX_VALUE;
        for (double currentValue : valueHashMap.keySet()) {
            if (currentValue < currentLow) {
                currentLow = currentValue;
            }
        }
        return currentLow;
    }

    public double calculateHighestValue(HashMap<Double, Integer> valueHashMap) {
        double currentHigh = 0;
        for (double currentValue : valueHashMap.keySet()) {
            if (currentValue > currentHigh) {
                currentHigh = currentValue;
            }
        }
        return currentHigh;
    }

}
