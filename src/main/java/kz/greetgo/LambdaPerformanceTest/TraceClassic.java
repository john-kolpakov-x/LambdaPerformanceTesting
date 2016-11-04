package kz.greetgo.LambdaPerformanceTest;

import org.apache.log4j.Logger;

public class TraceClassic {

  private final Logger logger;

  public boolean enabled = false;

  public TraceClassic(Logger logger) {
    this.logger = logger;
  }

  void trace(String message) {
    if (enabled) logger.info(message);
  }

}
