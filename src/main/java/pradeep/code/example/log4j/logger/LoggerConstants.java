package pradeep.code.example.log4j.logger;

public class LoggerConstants {

	private LoggerConstants() {
	}

	// PATTERN
	public static final String PATTERN_MESSAGE = "%date %level %msg%xEx%n";
	public static final String PATTERN_FILE = "%d{yyyy-MM-dd}-%i";

	// BUILDER
	public static final String BUILDER_ROLLING_FILE = "RollingFileBuilder";

	// PLUGIN
	public static final String PLUGIN_NAME_PROD = "production";
	public static final String PLUGIN_NAME_ERROR = "error";
	public static final String PLUGIN_NAME_DEBUG = "debug";
	public static final String PLUGIN_NAME_INFO = "info";
	public static final String PLUGIN_NAME_WARN = "warn";
	public static final String PLUGIN_NAME_FATAL = "fatal";
	public static final String PLUGIN_NAME_TRACE = "trace";
	public static final String PLUGIN_ROLLING_FILE = "RollingFile";

	// LAYOUT
	public static final String LAYOUT_PATTERN = "PatternLayout";

	// ATTRIBUTE
	public static final String ATTRIBUTE_PATTERN = "pattern";
	public static final String ATTRIBUTE_INTERVAl = "interval";
	public static final String ATTRIBUTE_MODULATE = "modulate";
	public static final String ATTRIBUTE_SIZE = "size";
	public static final String ATTRIBUTE_FILE_NAME = "fileName";
	public static final String ATTRIBUTE_FILE_PATTERN = "filePattern";
	public static final String ATTRIBUTE_ADDITIVITY = "additivity";

	// COMPONENT
	public static final String COMPONENT_POLICIES = "Policies";
	public static final String COMPONENT_TRIGGER_POLICY_TIME_BASED = "TimeBasedTriggeringPolicy";
	public static final String COMPONENT_TRIGGER_POLICY_SIZE_BASED = "SizeBasedTriggeringPolicy";

	// LOGGER
	public static final String LOGGER_ERROR = "ERRORLOGGER";
	public static final String LOGGER_DEBUG = "DEBUGLOGGER";
	public static final String LOGGER_PROD = "PRODLOGGER";
	public static final String LOGGER_INFO = "INFOLOGGER";
	public static final String LOGGER_WARN = "WARNLOGGER";
	public static final String LOGGER_FATAL = "FATALLOGGER";
	public static final String LOGGER_TRACE = "TRACELOGGER";
	
	public static final String PROD_FALSE = "false";
	public static final String PROD_TRUE = "true";
	public static final String FOLDER_PATH = "/home/mwt/Pradeep/zlogs/";
}
