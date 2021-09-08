package com.experis.test.carritodecompras.utilidades;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BLog {

	private static final String LOG_PROPERTIES_FILE = "log4j.properties";

	private static BLog loggerManagerFactory;

	private Logger lMSystem;

	private BLog() {
		Properties logProperties = new Properties();
		try {
			logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
			PropertyConfigurator.configure(logProperties);
			initLoggers();
		} catch (Exception e) {
			System.out.println("Unable to load logging property " + LOG_PROPERTIES_FILE);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private void initLoggers() {
		lMSystem = Logger.getLogger("sys");
	}

	/**
	 * @return MessageServiceFactory
	 */
	private synchronized static BLog getInstance() {
		if (loggerManagerFactory == null) {
			loggerManagerFactory = new BLog();
		}
		return loggerManagerFactory;
	}

	/**
	 * @return Logger
	 */
	public static Logger getLogger() {
		return getInstance().lMSystem;
	}

}
