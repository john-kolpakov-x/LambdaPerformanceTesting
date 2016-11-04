package kz.greetgo.LambdaPerformanceTest;

import org.apache.log4j.Logger;

import java.util.function.Supplier;

public class TraceLambda {
  private final Logger logger;

  public boolean enabled = false;

  public TraceLambda(Logger logger) {
    this.logger = logger;
  }

  void trace(Supplier<String> messageSupplier) {
    if (enabled) logger.info(messageSupplier.get());
  }
}
