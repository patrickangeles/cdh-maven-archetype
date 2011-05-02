/**
 * Copyright (c) 2011, Cloudera, Inc. All Rights Reserved.
 *
 * Cloudera, Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"). You may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the
 * License.
 */
package thiswillbereplaced;

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
