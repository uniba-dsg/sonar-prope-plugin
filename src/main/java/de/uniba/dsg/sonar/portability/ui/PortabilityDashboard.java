package de.uniba.dsg.sonar.portability.ui;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.web.Dashboard;
import org.sonar.api.web.Dashboard.Widget;

import de.uniba.dsg.sonar.portability.PortabilityMetrics;

import org.sonar.api.web.DashboardLayout;
import org.sonar.api.web.DashboardTemplate;

public final class PortabilityDashboard extends DashboardTemplate {

    private static final String METRIC1 = "metric1";
    private static final String METRIC2 = "metric2";
    private static final String METRIC3 = "metric3";
    private static final String METRIC4 = "metric4";
    private static final String METRIC5 = "metric5";
    private static final String METRIC6 = "metric6";

    @Override
    public Dashboard createDashboard() {
        Dashboard dashboard = Dashboard.create();
        dashboard.setLayout(DashboardLayout.TWO_COLUMNS_70_30);
        addWidgets(dashboard);
        return dashboard;
    }

    @Override
    public String getName() {
        return "Portability Dashboard";
    }

    private void addWidgets(Dashboard dashboard) {
        dashboard.addWidget("size", 1);

        dashboard.addWidget("rules", 1);

        Widget portabilityTimeMachineWidget = addTimeMachineWidged(dashboard, 1);
        portabilityTimeMachineWidget.setProperty("title", "Average Portability Values");
        portabilityTimeMachineWidget.setProperty(METRIC1, PortabilityMetrics.AVG_CLASSIFICATION_KEY);
        portabilityTimeMachineWidget.setProperty(METRIC2, PortabilityMetrics.AVG_NUMBER_OF_ELEMENTS_KEY);
        portabilityTimeMachineWidget.setProperty(METRIC3, PortabilityMetrics.AVG_BASIC_PORTABILITY_KEY);
        portabilityTimeMachineWidget.setProperty(METRIC4, PortabilityMetrics.AVG_ACTIVITY_PORTABILITY_KEY);
        portabilityTimeMachineWidget.setProperty(METRIC5, PortabilityMetrics.AVG_WEIGHTED_ELEMENT_KEY);
        portabilityTimeMachineWidget.setProperty(METRIC6, PortabilityMetrics.AVG_SERVICE_COMMUNICATION_KEY);

        Widget timelineWidget = dashboard.addWidget("timeline", 1);
        timelineWidget.setProperty("chartTitle", "Change Overview");
        timelineWidget.setProperty(METRIC1, PortabilityMetrics.AVG_BASIC_PORTABILITY_KEY);
        timelineWidget.setProperty(METRIC2, PortabilityMetrics.AVG_NUMBER_OF_ELEMENTS_KEY);
        timelineWidget.setProperty(METRIC3, PortabilityMetrics.AVG_ACTIVITY_PORTABILITY_KEY);
        timelineWidget.setProperty("height", "500");

        Widget sizeTimeMachineWidget = addTimeMachineWidged(dashboard, 2);
        sizeTimeMachineWidget.setProperty("title", "Standard Size Metrics");
        sizeTimeMachineWidget.setProperty(METRIC1, CoreMetrics.LINES_KEY);
        sizeTimeMachineWidget.setProperty(METRIC2, CoreMetrics.FILES_KEY);

        Widget maxValueTimeMachineWidget = addTimeMachineWidged(dashboard, 2);
        maxValueTimeMachineWidget.setProperty("title", "Highest Portability Values");
        maxValueTimeMachineWidget.setProperty(METRIC1, PortabilityMetrics.MAX_CLASSIFICATION_KEY);
        maxValueTimeMachineWidget.setProperty(METRIC2, PortabilityMetrics.MAX_NUMBER_OF_ELEMENTS_KEY);
        maxValueTimeMachineWidget.setProperty(METRIC3, PortabilityMetrics.MAX_BASIC_PORTABILITY_KEY);
        maxValueTimeMachineWidget.setProperty(METRIC4, PortabilityMetrics.MAX_ACTIVITY_PORTABILITY_KEY);
        maxValueTimeMachineWidget.setProperty(METRIC5, PortabilityMetrics.MAX_WEIGHTED_ELEMENT_KEY);
        maxValueTimeMachineWidget.setProperty(METRIC6, PortabilityMetrics.MAX_SERVICE_COMMUNICATION_KEY);

        Widget minValueTimeMachineWidget = addTimeMachineWidged(dashboard, 2);
        minValueTimeMachineWidget.setProperty("title", "Lowest Portability Values");
        minValueTimeMachineWidget.setProperty(METRIC1, PortabilityMetrics.MIN_CLASSIFICATION_KEY);
        minValueTimeMachineWidget.setProperty(METRIC2, PortabilityMetrics.MIN_NUMBER_OF_ELEMENTS_KEY);
        minValueTimeMachineWidget.setProperty(METRIC3, PortabilityMetrics.MIN_BASIC_PORTABILITY_KEY);
        minValueTimeMachineWidget.setProperty(METRIC4, PortabilityMetrics.MIN_ACTIVITY_PORTABILITY_KEY);
        minValueTimeMachineWidget.setProperty(METRIC5, PortabilityMetrics.MIN_WEIGHTED_ELEMENT_KEY);
        minValueTimeMachineWidget.setProperty(METRIC6, PortabilityMetrics.MIN_SERVICE_COMMUNICATION_KEY);

        Widget medianValueTimeMachineWidget = addTimeMachineWidged(dashboard, 2);
        medianValueTimeMachineWidget.setProperty("title", "Median Portability Values");
        medianValueTimeMachineWidget.setProperty(METRIC1, PortabilityMetrics.MED_CLASSIFICATION_KEY);
        medianValueTimeMachineWidget.setProperty(METRIC2, PortabilityMetrics.MED_NUMBER_OF_ELEMENTS_KEY);
        medianValueTimeMachineWidget.setProperty(METRIC3, PortabilityMetrics.MED_BASIC_PORTABILITY_KEY);
        medianValueTimeMachineWidget.setProperty(METRIC4, PortabilityMetrics.MED_ACTIVITY_PORTABILITY_KEY);
        medianValueTimeMachineWidget.setProperty(METRIC5, PortabilityMetrics.MED_WEIGHTED_ELEMENT_KEY);
        medianValueTimeMachineWidget.setProperty(METRIC6, PortabilityMetrics.MED_SERVICE_COMMUNICATION_KEY);
    }

    private Widget addTimeMachineWidged(Dashboard dashboard, int columnIndex) {
        Widget widget = dashboard.addWidget("time_machine", columnIndex);
        widget.setProperty("displaySparkLine", "true");
        return widget;
    }
}
