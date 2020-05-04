package com.revature.rideshare.services.impl;

import com.revature.rideshare.services.LoggerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoggerServiceImpl implements LoggerService {
	private Logger accessDeniedLog;
	private Logger businessLog;
	private Logger exceptionLog;
	private Logger performanceLog;

	public Logger getAccess() {
		if (accessDeniedLog == null) {
			accessDeniedLog = LogManager.getLogger("accessLogger");
		}
		return accessDeniedLog;
	}

	public Logger getBusiness() {
		if (businessLog == null) {
			businessLog = LogManager.getLogger("businessLogger");
		}
		return businessLog;
	}

	public Logger getException() {
		if (exceptionLog == null) {
			exceptionLog = LogManager.getLogger("exceptionLogger");
		}
		return exceptionLog;
	}

	public Logger getPerformance() {
		if (performanceLog == null) {
			performanceLog = LogManager.getLogger("performanceLogger");
		}
		return performanceLog;
	}
}
