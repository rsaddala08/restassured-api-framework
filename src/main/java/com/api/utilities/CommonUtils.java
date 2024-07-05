/**
 * This class  contains Utility methods specific to the framework architechture and
 * will be used to perform various actions across the framework
 *
 * @author Subrato
 */
package com.api.utilities;
import java.io.File;
import java.io.FileInputStream;
import com.api.reports.ExtentManager;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.io.FileUtils;
import org.fluttercode.datafactory.impl.DataFactory;

import java.security.SecureRandom;
import java.util.*;


public class CommonUtils {
	static ArrayList<String> myArray = new ArrayList<String>();

	/**
	 *
	 * @param strVal
	 * @return
	 */
	public static String getProperty(String strVal) {
		String val;
		try {
			String path = System.getProperty("user.dir") + "/EnvironmentDetails/config.properties";
			Properties prop = new Properties();
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
			val = prop.getProperty(strVal).trim();
		} catch (Throwable t) {
			val = null;
		}
		return val;
	}
	/**
	 *
	 * @return
	 */
	public static String htmlReportPathGenerated() {
		try {
			return ExtentManager.dynamicHtmlReportPath;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	public static String getRandomString(int len) {
		String CHARACTER_SET="0123456789abcdefghijklmnopqrstuvwxyz";
		SecureRandom random = new SecureRandom();
		StringBuffer buff = new StringBuffer(len);
		for(int i = 0; i < len; i++) {
			int offset = random.nextInt(CHARACTER_SET.length());
			buff.append(CHARACTER_SET.substring(offset,offset+1));
		}
		return buff.toString();
	}

	/**
	 *
	 * @param dir
	 */
	public static void deleteDirectory (String dir) {
		try {
			FileUtils.deleteDirectory(new File(dir));
		} catch (Throwable t) {

		}
	}
	public static void RandomNames(String Name) {
		DataFactory df = new DataFactory();
		Random rand = new Random();
		String Value = null;
		for (int i = 0; i < 2000; i++) {
			switch (Name) {
			case "RAND_FirstName":
				Value = df.getFirstName();
				break;
			case "RAND_LastName":
				Value = df.getLastName();
				break;
			case "RAND_Company":
				Value = df.getBusinessName();
				break;
				case "FullName":
					Value = df.getName();
					break;
			case "NUMBER":
				int num = rand.nextInt(9999);
				num += 1;
				String val = Integer.toString(num);

				Value = val + df.getFirstName();

				break;
			}

			myArray.add(Value);
		}

	}

	public static String getRandomName(String Name) {
		RandomNames(Name);
		Random generator = new Random();
		int randomIndex = generator.nextInt(myArray.size());
		String value = myArray.get(randomIndex);
		myArray.clear();
		return value;
	}

}