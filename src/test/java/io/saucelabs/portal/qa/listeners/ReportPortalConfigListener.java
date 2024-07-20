package io.saucelabs.portal.qa.listeners;

import com.epam.reportportal.testng.BaseTestNGListener;
import io.saucelabs.portal.qa.services.reportportal.ReportPortalOverrideParameters;

public class ReportPortalConfigListener extends BaseTestNGListener {

    public ReportPortalConfigListener() {
        super(new ReportPortalOverrideParameters());
    }
}
