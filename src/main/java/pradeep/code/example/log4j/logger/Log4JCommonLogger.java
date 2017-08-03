package pradeep.code.example.log4j.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Log4JCommonLogger {

	private static volatile Log4JCommonLogger commonLoggerInstance = null;

	private static StringWriter sw = new StringWriter();
	private static PrintWriter pw = new PrintWriter(sw);

	private static String folderPath = LoggerConstants.FOLDER_PATH;
	private static String isProduction = LoggerConstants.PROD_FALSE;

	private Logger loggerAudit = null;
	private Logger loggerError = null;
	private Logger loggerInfo = null;
	private Logger loggerWarn = null;
	private Logger loggerFatal = null;
	private Logger loggerTrace = null;
	private Logger loggerProd = null;

	private LoggerContext logContext;

	public static synchronized Log4JCommonLogger getInstance() {
		if (commonLoggerInstance == null) {
			commonLoggerInstance = new Log4JCommonLogger();
		}
		return commonLoggerInstance;
	}

	private Log4JCommonLogger() {
		readConfig();
		logContext = Configurator.initialize(createRollingFileConfiguration());
		logContext.start();
		loggerError = LogManager.getLogger(LoggerConstants.LOGGER_ERROR);
		loggerAudit = LogManager.getLogger(LoggerConstants.LOGGER_DEBUG);
		loggerInfo = LogManager.getLogger(LoggerConstants.LOGGER_INFO);
		loggerWarn = LogManager.getLogger(LoggerConstants.LOGGER_WARN);
		loggerFatal = LogManager.getLogger(LoggerConstants.LOGGER_FATAL);
		loggerTrace = LogManager.getLogger(LoggerConstants.LOGGER_TRACE);
		loggerProd = LogManager.getLogger(LoggerConstants.LOGGER_PROD);
		
	}

	// read to set configurations like log folder path, production mode etc
	private static void readConfig() {
	}

	public void closeLoggerContext() {
		if ((logContext != null) && (logContext.isStarted())) {
			logContext.close();
		}
	}

	private static Configuration createRollingFileConfiguration() {

		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
		builder.setStatusLevel(Level.ERROR);
		builder.setConfigurationName(LoggerConstants.BUILDER_ROLLING_FILE);

		// creating message pattern
		LayoutComponentBuilder layoutBuilder = builder.newLayout(LoggerConstants.LAYOUT_PATTERN)
				.addAttribute(LoggerConstants.ATTRIBUTE_PATTERN, LoggerConstants.PATTERN_MESSAGE);

		// creating trigger policy
		@SuppressWarnings("rawtypes")
		ComponentBuilder triggeringPolicy = builder.newComponent(LoggerConstants.COMPONENT_POLICIES)
				.addComponent(builder.newComponent(LoggerConstants.COMPONENT_TRIGGER_POLICY_TIME_BASED)
						.addAttribute(LoggerConstants.ATTRIBUTE_INTERVAl, "1")
						.addAttribute(LoggerConstants.ATTRIBUTE_MODULATE, true))
				.addComponent(builder.newComponent(LoggerConstants.COMPONENT_TRIGGER_POLICY_SIZE_BASED)
						.addAttribute(LoggerConstants.ATTRIBUTE_SIZE, "100M"));

		// creating file rolling appender for different log files
		// error appender
		AppenderComponentBuilder appenderBuilder = builder
				.newAppender(LoggerConstants.PLUGIN_NAME_ERROR, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jerror.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jerror - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);

		// debug appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_DEBUG, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jdebug.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jdebug - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);

		// info appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_INFO, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jinfo.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jinfo - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);

		// warning appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_WARN, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jwarn.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jwarn - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);

		// production appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_PROD, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jprod.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jprod - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);
		
		// fatal appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_FATAL, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jfatal.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jfatal - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);
		
		// trace appender
		appenderBuilder = builder.newAppender(LoggerConstants.PLUGIN_NAME_TRACE, LoggerConstants.PLUGIN_ROLLING_FILE)
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_NAME, folderPath + "log4jtrace.log")
				.addAttribute(LoggerConstants.ATTRIBUTE_FILE_PATTERN,
						folderPath + "log4jtrace - " + LoggerConstants.PATTERN_FILE + ".log")
				.add(layoutBuilder).addComponent(triggeringPolicy);
		builder.add(appenderBuilder);

		// creating logger for different log files
		builder.add(builder.newLogger(LoggerConstants.LOGGER_ERROR, Level.ERROR)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_ERROR))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));

		builder.add(builder.newLogger(LoggerConstants.LOGGER_DEBUG, Level.DEBUG)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_DEBUG))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));

		builder.add(builder.newLogger(LoggerConstants.LOGGER_INFO, Level.INFO)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_INFO))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));

		builder.add(builder.newLogger(LoggerConstants.LOGGER_WARN, Level.WARN)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_WARN))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));

		builder.add(builder.newLogger(LoggerConstants.LOGGER_PROD, Level.INFO)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_PROD))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));
		
		builder.add(builder.newLogger(LoggerConstants.LOGGER_FATAL, Level.FATAL)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_FATAL))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));
		
		builder.add(builder.newLogger(LoggerConstants.LOGGER_TRACE, Level.TRACE)
				.add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_TRACE))
				.addAttribute(LoggerConstants.ATTRIBUTE_ADDITIVITY, false));

		builder.add(builder.newRootLogger(Level.DEBUG).add(builder.newAppenderRef(LoggerConstants.PLUGIN_NAME_ERROR)));

		return builder.build();
	}

	private String getMessage(String className, String methodName, String message) {
		return className + "." + methodName + " : " + message;
	}

	public void exceptionLogger(String className, String methodName, Exception exception) {
		if (exception != null) {
			exception.printStackTrace(pw);
		}
		loggerError.error(getMessage(className, methodName, sw.toString()));
	}

	public void errorLogger(String className, String methodName, String message) {
		if (isProduction.equalsIgnoreCase(LoggerConstants.PROD_FALSE)) {
			loggerError.error(getMessage(className, methodName, message));
		}
	}

	public void debugLogger(String className, String methodName, String message) {
		if (isProduction.equalsIgnoreCase(LoggerConstants.PROD_FALSE)) {
			loggerAudit.debug(getMessage(className, methodName, message));
		}
	}

	public void infoLogger(String className, String methodName, String message) {
		if (isProduction.equalsIgnoreCase(LoggerConstants.PROD_FALSE)) {
			loggerInfo.info(getMessage(className, methodName, message));
		}
	}

	public void warningLogger(String className, String methodName, String message) {
		if (isProduction.equalsIgnoreCase(LoggerConstants.PROD_FALSE)) {
			loggerWarn.warn(getMessage(className, methodName, message));
		}
	}

	public void prodLogger(String className, String methodName, String message) {
		loggerProd.info(getMessage(className, methodName, message));
	}
	
	public void fatalLogger(String className, String methodName, String message) {
		loggerFatal.fatal(getMessage(className, methodName, message));
	}
	
	public void traceLogger(String className, String methodName, String message) {
		if (isProduction.equalsIgnoreCase(LoggerConstants.PROD_FALSE)) {
			loggerTrace.trace(getMessage(className, methodName, message));
		}
	}
}