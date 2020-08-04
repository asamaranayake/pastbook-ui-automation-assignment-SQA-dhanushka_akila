package com.pastbook.automation.util;
/**
 * @author Akila
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

	private static ConfigUtil instance = new ConfigUtil();
	private Properties prop;

	public ConfigUtil() {

		prop = new Properties();
		try {

			prop.load(new FileInputStream(new File("resources/Config.properties")));

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static ConfigUtil getConfigUtil() {
		return instance;

	}

	public String getProperty(String Key) {

		return prop.getProperty(Key);
	}

}