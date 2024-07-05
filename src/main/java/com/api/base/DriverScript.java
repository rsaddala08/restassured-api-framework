package com.api.base;

import java.io.IOException;

import com.api.console.Logging;
import org.testng.annotations.*;
import com.api.reports.ReportUtil;
import com.api.support.Xls_Reader;
import com.api.utilities.Constants;
import com.api.utilities.CommonUtils;
import com.api.utilities.Zip;

public class DriverScript {

	public static String TEST_DATA_PATH = Constants.FRAMEWORK_ROOT_DIRECTORY + "/src/main/resources/testData";
	public static Xls_Reader xls = null, xlsController = new Xls_Reader(TEST_DATA_PATH + Constants.FILE_SEPARATOR_KEY + "controller.xlsx");
	public static int rowNum = 2, rowNumController = 2;
	public static int rowNumExecutableTC = 2;
	public static int count = 0;
	public static String testCaseName;
	public static String testCaseId;
	public static boolean continueRun = false;
	public static String endpoint = null;
	public static final String BASE_URL = CommonUtils.getProperty("BASE_URL");

	/**
	 *
	 * @return
	 */
	public static int getRowNumForExecutableTestCases() {
		while (rowNumExecutableTC <= xlsController.getRowCount(Constants.TEST_DATA)) {
			if (xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC).toUpperCase().equals(Constants.TEST_CASE_RUNMODE_YES)) {
				count++;
			}
			rowNumExecutableTC++;
		}
		rowNumExecutableTC = 2;
		return count;
	}

	/**
	 *
	 * @param tcId
	 * @return
	 */
	public static boolean isRunnable(String tcId) {
		boolean isRunnable = false;
		continueRun = false;
		rowNumController = xlsController.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
		rowNum = rowNumController;
		testCaseId = tcId;
		testCaseName = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
		if (xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
			endpoint = null;
			continueRun = true;
			xls = new Xls_Reader(TEST_DATA_PATH + Constants.FILE_SEPARATOR_KEY + "api_data.xlsx");
			endpoint = BASE_URL + xls.getCellData(Constants.TEST_DATA, Constants.ENDPOINT, rowNum);
			isRunnable = true;
			Logging.info("Test scenario started:==== " + testCaseId + ": " + testCaseName);
		} else {
			Logging.info("Please check the runmode of TestCaseID '" + testCaseId + "'");
			isRunnable = false;
		}
		return isRunnable;
	}

	public static final int countOfExecutableTestCases = getRowNumForExecutableTestCases();

	public static String getTestDataSheetName() {
		String testDataSheet = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_DATA_FILE_NAME, rowNum);
		return testDataSheet;
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() {
		ReportUtil.test = null;
	}

	@BeforeSuite
	public void init () {
		CommonUtils.deleteDirectory(Constants.FRAMEWORK_ROOT_DIRECTORY + "/Results");
	}

	@AfterSuite
	public void afterClass() throws IOException {;
		Zip.zipFile();
	}


}