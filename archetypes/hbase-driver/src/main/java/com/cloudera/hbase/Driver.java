package com.cloudera.hbase;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {

  public static void main(String argv[]) {
    int exitCode = -1;
    ProgramDriver pgd = new ProgramDriver();
    try {
      pgd.addClass("wordcount", WordCount.class,
          "MapReduce program to count word frequencies.");
      pgd.addClass("oldwordcount", OldWordCount.class,
          "MapReduce program to count word frequencies (old API).");
      pgd.driver(argv);

      // Success
      exitCode = 0;
    } catch (Throwable e) {
      e.printStackTrace();
    }

    System.exit(exitCode);
  }
}
