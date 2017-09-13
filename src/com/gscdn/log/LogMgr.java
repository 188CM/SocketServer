package com.gscdn.log;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogMgr {

	private static LogMgr LogInstance = new LogMgr();

	private static Logger LOG;

	private LogMgr() {
		LOG = Logger.getLogger("Server");
	}
	
	public static LogMgr getLogInstance() {
		if (LogInstance == null) {
//			synchronized (LogMgr.class) {
				if (LogInstance == null)
					LogInstance = new LogMgr();
//			}
		}
		return LogInstance;
	}

	public void info(String format, Object... vals) {
		String msg = MessageFormat.format(format, vals);
		LOG.info(msg);
	}

	public void error(String format, Throwable err, Object... vals) {
		String msg = MessageFormat.format(format, vals);
		LOG.log(Level.SEVERE, msg, err);
	}

}
