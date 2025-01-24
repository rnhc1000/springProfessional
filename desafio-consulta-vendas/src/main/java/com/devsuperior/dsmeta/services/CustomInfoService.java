package com.devsuperior.dsmeta.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

public class CustomInfoService {

private static final Logger logger = LoggerFactory.getLogger(CustomInfoService.class);

  public void infoSystem() {
    final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZonedDateTime.now().getZone());
    final String javaVersion = System.getProperty("java.version");
    final int numOfCores = Runtime.getRuntime().availableProcessors();

    if (logger.isInfoEnabled()) {
      logger.info("Sales Challenge started running at zone {}, running java version {}, on top of {} cores",
          zonedDateTime,
          javaVersion,
          numOfCores
      );
    }
  }
}
