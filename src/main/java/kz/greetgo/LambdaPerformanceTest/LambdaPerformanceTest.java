package kz.greetgo.LambdaPerformanceTest;

import java.io.File;

import static kz.greetgo.LambdaPerformanceTest.Utils.createLogger;
import static kz.greetgo.LambdaPerformanceTest.Utils.nanoToShow;

public class LambdaPerformanceTest {

  public static void main(String[] args) throws Exception {
    File logFileClassic = new File("build/logs/classic.log");
    File logFileLambda = new File("build/logs/lambda.log");

    logFileLambda.delete();
    logFileClassic.delete();

    TraceClassic classic = new TraceClassic(createLogger(logFileClassic));
    TraceLambda lambda = new TraceLambda(createLogger(logFileLambda));

    final int N = 3_000_000;

    System.out.println("//");
    System.out.println("// Performance test with printing to log files...");
    System.out.println("//");

    classic.enabled = true;
    lambda.enabled = true;

    {
      long startedAt = System.nanoTime();

      for (int i = 0; i < N; i++) {
        classic.trace("Message " + i);
      }

      long nanoTime = System.nanoTime() - startedAt;

      System.out.println("     Traced " + N + " lines in classic mode for " + nanoToShow(nanoTime));
    }

    {
      long startedAt = System.nanoTime();

      for (int i = 0; i < N; i++) {
        final int I = i;
        lambda.trace(() -> "Message " + I);
      }

      long nanoTime = System.nanoTime() - startedAt;

      System.out.println("     Traced " + N + " lines in lambda  mode for " + nanoToShow(nanoTime));
    }

    System.out.println("//");
    System.out.println("// Performance test withOUT printing to log files...");
    System.out.println("//");

    classic.enabled = false;
    lambda.enabled = false;

    {
      long startedAt = System.nanoTime();

      for (int i = 0; i < N; i++) {
        classic.trace("Message " + i);
      }

      long nanoTime = System.nanoTime() - startedAt;

      System.out.println("     Traced " + N + " lines in classic mode for " + nanoToShow(nanoTime));
    }

    {
      long startedAt = System.nanoTime();

      for (int i = 0; i < N; i++) {
        final int I = i;
        lambda.trace(() -> "Message " + I);
      }

      long nanoTime = System.nanoTime() - startedAt;

      System.out.println("     Traced " + N + " lines in lambda  mode for " + nanoToShow(nanoTime));
    }
  }
}
