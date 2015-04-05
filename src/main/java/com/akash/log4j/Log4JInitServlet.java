package com.akash.log4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;


//	http://logging.apache.org/log4j/1.2/manual.html
//	http://logging.apache.org/log4j/2.x/manual/
public class Log4JInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Log4JInitServlet is initializing log4j");
		String log4jLocation = config
				.getInitParameter("log4j-properties-location");

		if (log4jLocation == null) {
			System.err
					.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {

			File file = null;
			try {
				FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext();
				file = ctx.getResource(log4jLocation).getFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (file != null && file.exists()) {
				System.out.println("Initializing log4j with: "
						+ file.toString());
				PropertyConfigurator.configure(file.toString());
			} else {
				System.err
						.println("*** "
								+ log4jLocation
								+ " file not found, so initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the Log4JInitServlet<br/>");
		String logLevel = request.getParameter("logLevel");
		String reloadPropertiesFile = request
				.getParameter("reloadPropertiesFile");
		if (logLevel != null) {
			setLogLevelWithParameter(out, logLevel);
		} else if (reloadPropertiesFile != null) {
			out.println("Attempting to reload log4j properties file<br/>");
			loadLog4jPropertiesFile(out);
		} else {
			out.println("no logLevel or reloadPropertiesFile parameters were found<br/>");
		}
	}

	private void setLogLevelWithParameter(PrintWriter out, String logLevel) {
		Logger root = Logger.getRootLogger();
		boolean logLevelRecognized = true;
		if ("DEBUG".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.DEBUG);
		} else if ("INFO".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.INFO);
		} else if ("WARN".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.WARN);
		} else if ("ERROR".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.ERROR);
		} else if ("FATAL".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.FATAL);
		} else {
			logLevelRecognized = false;
		}

		if (logLevelRecognized) {
			out.println("Log level has been set to: " + logLevel + "<br/>");
		} else {
			out.println("logLevel parameter '" + logLevel
					+ "' level not recognized<br/>");
		}
	}

	private void loadLog4jPropertiesFile(PrintWriter out) {
		String log4jLocation = getInitParameter("log4j-properties-location");

		if (log4jLocation == null) {
			out.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator<br/>");
			BasicConfigurator.configure();
		} else {
			File file = null;
			try {
				FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext();
				file = ctx.getResource(log4jLocation).getFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (file != null && file.exists()) {
				System.out.println("Initializing log4j with: "
						+ file.toString());
				PropertyConfigurator.configure(file.toString());
			} else {
				System.err
						.println("*** "
								+ log4jLocation
								+ " file not found, so initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
	}
}