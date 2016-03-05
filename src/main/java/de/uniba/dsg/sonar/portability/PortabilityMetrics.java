package de.uniba.dsg.sonar.portability;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public final class PortabilityMetrics implements Metrics {

	// ############################ FILE METRICS #########################################
	public static final String CLASSIFICATION_KEY = "classification_key";
	public static final Metric<String> CLASSIFICATION = new Metric.Builder(CLASSIFICATION_KEY, "Classification",
			Metric.ValueType.STRING).setDescription("Classification of a file in Portability classes")
					.setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain("Portability").create();

	public static final String NUMBER_OF_ELEMENTS_KEY = "number_of_elements_key";
	public static final Metric<Integer> N = new Metric.Builder(NUMBER_OF_ELEMENTS_KEY, "Number of Elements",
			Metric.ValueType.INT).setDescription("Number of Elements in File").setDirection(Metric.DIRECTION_NONE)
					.setQualitative(false).setDomain("Portability").create();

	public static final String BASIC_PORTABILITY_KEY = "basic_portability_key";
	public static final Metric<Double> MB = new Metric.Builder(BASIC_PORTABILITY_KEY, "Basic Portability",
			Metric.ValueType.FLOAT).setDescription("Value between 0 and 1 for the Basic Portability")
					.setDirection(Metric.DIRECTION_BETTER).setQualitative(true).setDomain("Portability").create();

	public static final String WEIGHTED_ELEMENT_KEY = "weighted_element_key";
	public static final Metric<Double> ME = new Metric.Builder(WEIGHTED_ELEMENT_KEY, "Weighted Element",
			Metric.ValueType.FLOAT).setDescription("The Weightet Element Metric").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability").create();

	public static final String ACTIVITY_PORTABILITY_KEY = "activity_portability_key";
	public static final Metric<Double> MA = new Metric.Builder(ACTIVITY_PORTABILITY_KEY, "Activity Portability",
			Metric.ValueType.FLOAT).setDescription("The Activity Portability").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability").create();

	public static final String SERVICE_COMMUNICATION_KEY = "service_communication_key";
	public static final Metric<Double> MS = new Metric.Builder(SERVICE_COMMUNICATION_KEY, "Service Communication",
			Metric.ValueType.FLOAT).setDescription("The Service Communication").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability").create();

	// ################################ PROJECT METRICS #######################################
	
	// ############################## AVERAGE VALUES ###########################
	public static final String AVG_CLASSIFICATION_KEY = "avg_classification_key";
	public static final Metric<String> AVG_CLASSIFICATION = new Metric.Builder(AVG_CLASSIFICATION_KEY,
			"Avg Classification", Metric.ValueType.STRING).setDescription("most frequent appearance Classification")
					.setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain("Portability-Project")
					.create();

	public static final String AVG_NUMBER_OF_ELEMENTS_KEY = "avg_number_of_elements_key";
	public static final Metric<Double> AVG_N = new Metric.Builder(AVG_NUMBER_OF_ELEMENTS_KEY, "Avg. N of Elements",
			Metric.ValueType.FLOAT).setDescription("Average number of Elements").setDirection(Metric.DIRECTION_NONE)
					.setQualitative(false).setDomain("Portability-Project").create();

	public static final String AVG_BASIC_PORTABILITY_KEY = "avg_basic_portability_key";
	public static final Metric<Double> AVG_MB = new Metric.Builder(AVG_BASIC_PORTABILITY_KEY, "Avg. Basic Portability",
			Metric.ValueType.FLOAT).setDescription("Average Value between 0 and 1 for the Basic Portability")
					.setDirection(Metric.DIRECTION_BETTER).setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String AVG_WEIGHTED_ELEMENT_KEY = "avg_weighted_element_key";
	public static final Metric<Double> AVG_ME = new Metric.Builder(AVG_WEIGHTED_ELEMENT_KEY, "Avg. Weighted Element",
			Metric.ValueType.FLOAT).setDescription("The average Weightet Element Metric").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String AVG_ACTIVITY_PORTABILITY_KEY = "avg_activity_portability_key";
	public static final Metric<Double> AVG_MA = new Metric.Builder(AVG_ACTIVITY_PORTABILITY_KEY, "Avg. Activity Portability",
			Metric.ValueType.FLOAT).setDescription("The average Activity Portability").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();

	
	public static final String AVG_SERVICE_COMMUNICATION_KEY = "avg_service_communication_key";
	public static final Metric<Double> AVG_MS = new Metric.Builder(AVG_SERVICE_COMMUNICATION_KEY, "Avg. Service Communication",
			Metric.ValueType.FLOAT).setDescription("The average Service Communication").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	// ############################## HIGHEST VALUES ###########################
	public static final String MAX_CLASSIFICATION_KEY = "highest_classification_key";
	public static final Metric<String> MAX_CLASSIFICATION = new Metric.Builder(MAX_CLASSIFICATION_KEY,
			"Max Classification", Metric.ValueType.STRING).setDescription("Highest Classification")
					.setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain("Portability-Project")
					.create();
	
	public static final String MAX_NUMBER_OF_ELEMENTS_KEY = "max_number_of_elements_key";
	public static final Metric<Double>MAX_N = new Metric.Builder(MAX_NUMBER_OF_ELEMENTS_KEY, "Max. Number of Elements",
			Metric.ValueType.FLOAT).setDescription("Highest number of Elements").setDirection(Metric.DIRECTION_NONE)
					.setQualitative(false).setDomain("Portability-Project").create();
	
	public static final String MAX_BASIC_PORTABILITY_KEY = "max_basic_portability_key";
	public static final Metric<Double> MAX_MB = new Metric.Builder(MAX_BASIC_PORTABILITY_KEY, "Max. Basic Portability",
			Metric.ValueType.FLOAT).setDescription("Highest Value between 0 and 1 for the Basic Portability")
					.setDirection(Metric.DIRECTION_BETTER).setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MAX_WEIGHTED_ELEMENT_KEY = "max_weighted_element_key";
	public static final Metric<Double> MAX_ME = new Metric.Builder(MAX_WEIGHTED_ELEMENT_KEY, "Max. Weighted Element",
			Metric.ValueType.FLOAT).setDescription("The Highest Weightet Element Metric").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MAX_ACTIVITY_PORTABILITY_KEY = "max_activity_portability_key";
	public static final Metric<Double> MAX_MA = new Metric.Builder(MAX_ACTIVITY_PORTABILITY_KEY, "Max. Activity Portability",
			Metric.ValueType.FLOAT).setDescription("The highest Activity Portability").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MAX_SERVICE_COMMUNICATION_KEY = "max_service_communication_key";
	public static final Metric<Double> MAX_MS = new Metric.Builder(MAX_SERVICE_COMMUNICATION_KEY, "Max. Service Communication",
			Metric.ValueType.FLOAT).setDescription("The highest Service Communication").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	// ############################## LOWEST VALUES ###########################
	public static final String MIN_CLASSIFICATION_KEY = "min_classification_key";
	public static final Metric<String> MIN_CLASSIFICATION = new Metric.Builder(MIN_CLASSIFICATION_KEY,
			"Min Classification", Metric.ValueType.STRING).setDescription("Lowest Classification")
					.setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain("Portability-Project")
					.create();
	
	public static final String MIN_NUMBER_OF_ELEMENTS_KEY = "min_number_of_elements_key";
	public static final Metric<Double>MIN_N = new Metric.Builder(MIN_NUMBER_OF_ELEMENTS_KEY, "Min. Number of Elements",
			Metric.ValueType.FLOAT).setDescription("Lowest number of Elements").setDirection(Metric.DIRECTION_NONE)
					.setQualitative(false).setDomain("Portability-Project").create();
	
	public static final String MIN_BASIC_PORTABILITY_KEY = "min_basic_portability_key";
	public static final Metric<Double> MIN_MB = new Metric.Builder(MIN_BASIC_PORTABILITY_KEY, "Min. Basic Portability",
			Metric.ValueType.FLOAT).setDescription("Lowest Value between 0 and 1 for the Basic Portability")
					.setDirection(Metric.DIRECTION_BETTER).setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MIN_WEIGHTED_ELEMENT_KEY = "min_weighted_element_key";
	public static final Metric<Double> MIN_ME = new Metric.Builder(MIN_WEIGHTED_ELEMENT_KEY, "Min. Weighted Element",
			Metric.ValueType.FLOAT).setDescription("The Lowest Weightet Element Metric").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MIN_ACTIVITY_PORTABILITY_KEY = "min_activity_portability_key";
	public static final Metric<Double> MIN_MA = new Metric.Builder(MIN_ACTIVITY_PORTABILITY_KEY, "Min. Activity Portability",
			Metric.ValueType.FLOAT).setDescription("The lowest Activity Portability").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MIN_SERVICE_COMMUNICATION_KEY = "min_service_communication_key";
	public static final Metric<Double> MIN_MS = new Metric.Builder(MIN_SERVICE_COMMUNICATION_KEY, "Min. Service Communication",
			Metric.ValueType.FLOAT).setDescription("The lowest Service Communication").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();	
	
	// ########################### MEDIAN VALUES  ########################
	public static final String MED_CLASSIFICATION_KEY = "med_classification_key";
	public static final Metric<String> MED_CLASSIFICATION = new Metric.Builder(MED_CLASSIFICATION_KEY,
			"Median Classification", Metric.ValueType.STRING).setDescription("Median Classification")
					.setDirection(Metric.DIRECTION_WORST).setQualitative(false).setDomain("Portability-Project")
					.create();
	
	public static final String MED_NUMBER_OF_ELEMENTS_KEY = "med_number_of_elements_key";
	public static final Metric<Double>MED_N = new Metric.Builder(MED_NUMBER_OF_ELEMENTS_KEY, "Median Number of Elements",
			Metric.ValueType.FLOAT).setDescription("Median of Elements in Project").setDirection(Metric.DIRECTION_NONE)
					.setQualitative(false).setDomain("Portability-Project").create();
	
	public static final String MED_BASIC_PORTABILITY_KEY = "med_basic_portability_key";
	public static final Metric<Double> MED_MB = new Metric.Builder(MED_BASIC_PORTABILITY_KEY, "Median Basic Portability",
			Metric.ValueType.FLOAT).setDescription("Median Value for the Basic Portability")
					.setDirection(Metric.DIRECTION_BETTER).setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MED_WEIGHTED_ELEMENT_KEY = "med_weighted_element_key";
	public static final Metric<Double> MED_ME = new Metric.Builder(MED_WEIGHTED_ELEMENT_KEY, "Median Weighted Element",
			Metric.ValueType.FLOAT).setDescription("Median Weightet Element Metric").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MED_ACTIVITY_PORTABILITY_KEY = "med_activity_portability_key";
	public static final Metric<Double> MED_MA = new Metric.Builder(MED_ACTIVITY_PORTABILITY_KEY, "Median Activity Portability",
			Metric.ValueType.FLOAT).setDescription("Median Activity Portability").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();
	
	public static final String MED_SERVICE_COMMUNICATION_KEY = "med_service_communication_key";
	public static final Metric<Double> MED_MS = new Metric.Builder(MED_SERVICE_COMMUNICATION_KEY, "Median Service Communication",
			Metric.ValueType.FLOAT).setDescription("Median Service Communication").setDirection(Metric.DIRECTION_BETTER)
					.setQualitative(true).setDomain("Portability-Project").create();	

	// getMetrics() method is defined in the Metrics interface and is used by
	// Sonar to retrieve the list of new metrics
	@SuppressWarnings("rawtypes")
	@Override
	public List<Metric> getMetrics() {
		return Arrays.<Metric> asList(CLASSIFICATION, N, MB, ME, MA, MS,
				AVG_CLASSIFICATION, AVG_N, AVG_MB, AVG_ME, AVG_MA, AVG_MS,
				MAX_CLASSIFICATION, MAX_N, MAX_MB, MAX_ME, MAX_MA, MAX_MS,
				MIN_CLASSIFICATION, MIN_N, MIN_MB, MIN_ME, MIN_MA, MIN_MS,
				MED_CLASSIFICATION, MED_N, MED_MB, MED_ME, MED_MA, MED_MS);
	}
}
