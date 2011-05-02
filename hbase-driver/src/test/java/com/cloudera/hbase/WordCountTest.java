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
package com.cloudera.hbase;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.cloudera.hbase.WordCount;

public class WordCountTest extends TestCase {
  private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
  private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
  private Mapper<LongWritable, Text, Text, IntWritable> mapper;
  private Reducer<Text, IntWritable, Text, IntWritable> reducer;

  private static final LongWritable one = new LongWritable(1);

  @Override
  @Before
  public void setUp() {
    mapper = new WordCount.Map();
    reducer = new WordCount.Reduce();
    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>(mapper);
    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(
        reducer);
    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>(
        mapper, reducer);
  }

  @Test
  public void testMapper() {
    mapDriver.withInput(one, new Text("one two three")).withOutput(
        new Text("one"), new IntWritable(1)).withOutput(new Text("two"),
        new IntWritable(1)).withOutput(new Text("three"), new IntWritable(1))
        .runTest();
  }

  @Test
  public void testReducer() {
    List<IntWritable> counts = Arrays.asList(new IntWritable(3),
        new IntWritable(4));
    reduceDriver.withInput(new Text("one"), counts).withOutput(new Text("one"),
        new IntWritable(7)).runTest();
  }

  @Test
  public void testMapReduce() {
    mapReduceDriver.withInput(one, new Text("b c a b c c")).withOutput(
        new Text("a"), new IntWritable(1)).withOutput(new Text("b"),
        new IntWritable(2)).withOutput(new Text("c"), new IntWritable(3))
        .runTest();
  }

}
