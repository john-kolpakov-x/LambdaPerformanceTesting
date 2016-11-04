package kz.greetgo.LambdaPerformanceTest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Utils {
  public static Logger createLogger(File file) throws Exception {
    RollingFileAppender appender = new RollingFileAppender();
    appender.setFile(file.getPath(), true, true, 4000);
    appender.setThreshold(Level.INFO);
    appender.setMaxFileSize("100MB");
    appender.setMaxBackupIndex(100);

    PatternLayout layout = new PatternLayout();
    layout.setConversionPattern("%d %-5p [%c{1}] %m%n");
    appender.setLayout(layout);

    Logger logger = Logger.getLogger(file.getName());
    logger.addAppender(appender);

    return logger;
  }

  public static final double GIG = 1_000_000_000.0;

  public static String nanoToShow(long nanoTime) {
    DecimalFormat f = new DecimalFormat("###,###.###");
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setDecimalSeparator('.');
    dfs.setGroupingSeparator(' ');
    f.setDecimalFormatSymbols(dfs);
    return f.format(nanoTime / GIG) + " seconds";
  }


  public static void main(String[] args) {
    System.out.println(nanoToShow(4324231432141234L));
  }
}
