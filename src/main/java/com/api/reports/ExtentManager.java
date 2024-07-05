package com.api.reports;
import java.io.File;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static String dynamicHtmlReportPath;
	public static String reportFolderPath = null;

	static String ENV = CommonUtils.getProperty("ENV");

	public static ExtentReports getInstance() {
		if (extent == null) {
			dynamicHtmlReportPath = htmlReportPath();
			extent.loadConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"));
			try {
				extent
						.addSystemInfo("Environment", ENV);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return extent;
	}


	public static String htmlReportPath() {
		String fileName = Constants.EXECUTION_REPORT_FILE_NAME + ".html";
		reportFolderPath = System.getProperty("user.dir") + Constants.FILE_SEPARATOR_KEY + "Results";
		File dir = new File(reportFolderPath);
		dir.mkdir();
		extent = new ExtentReports(reportFolderPath + Constants.FILE_SEPARATOR_KEY + fileName);
		return (reportFolderPath + File.separator + fileName);
	}

}