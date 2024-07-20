package io.saucelabs.portal.qa.services.reportportal;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import org.testng.util.Strings;

import java.util.Calendar;
import java.util.function.Supplier;

public class ReportPortalOverrideParameters extends TestNGService {


    public ReportPortalOverrideParameters() {
        super(getReportPortalProperties());
    }

    public static Supplier<Launch> getReportPortalProperties() {
        ListenerParameters parameters = new ListenerParameters(PropertiesLoader.load());
        parameters.setBaseUrl(ReportPortalConstants.REPORT_PORTAL_HOST);
        parameters.setApiKey(ReportPortalConstants.REPORT_PORTAL_WEB_API_KEY);
        parameters.setLaunchName(ReportPortalConstants.REPORT_PORTAL_WEB_LAUNCH + "_" + System.getProperty(SauceLabsPortalConstants.RUN_MODE));
        parameters.setProjectName(ReportPortalConstants.REPORT_PORTAL_WEB_PROJECT_NAME);
        ReportPortal reportPortal = ReportPortal.builder().withParameters(parameters).build();
        StartLaunchRQ startLaunch = buildReportPortalLaunch(reportPortal.getParameters());
        return () -> reportPortal.newLaunch(startLaunch);
    }

    private static StartLaunchRQ buildReportPortalLaunch(ListenerParameters parameters) {
        StartLaunchRQ launchStart = new StartLaunchRQ();
        launchStart.setName(parameters.getLaunchName());
        launchStart.setStartTime(Calendar.getInstance().getTime());
        launchStart.setAttributes(parameters.getAttributes());
        launchStart.setMode(parameters.getLaunchRunningMode());
        if (!Strings.isNullOrEmpty(parameters.getDescription()))
            launchStart.setDescription(parameters.getDescription());
        return launchStart;
    }
}
