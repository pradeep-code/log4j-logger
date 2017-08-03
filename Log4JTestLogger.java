import java.io.IOException;

import pradeep.code.example.log4j.logger.Log4JCommonLogger;
public class Log4JTestLogger {
 
	public static void main(String[] args) {
		Log4JCommonLogger.getInstance().exceptionLogger(Log4JTestLogger.class.getName(),"main", new IOException());
		Log4JCommonLogger.getInstance().errorLogger(Log4JTestLogger.class.getName(),"main", "Error Message");
		Log4JCommonLogger.getInstance().debugLogger(Log4JTestLogger.class.getName(),"main", "Debug Message");
		Log4JCommonLogger.getInstance().infoLogger(Log4JTestLogger.class.getName(),"main", "Info Message");
		Log4JCommonLogger.getInstance().warningLogger(Log4JTestLogger.class.getName(),"main", "Warn Message");
		Log4JCommonLogger.getInstance().prodLogger(Log4JTestLogger.class.getName(),"main", "Prod Message");
		Log4JCommonLogger.getInstance().fatalLogger(Log4JTestLogger.class.getName(),"main", "Fatal Message");
		Log4JCommonLogger.getInstance().traceLogger(Log4JTestLogger.class.getName(),"main", "trace Message");
		Log4JCommonLogger.getInstance().closeLoggerContext();
	}
}
