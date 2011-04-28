package thiswillbereplaced;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import thiswillbereplaced.OldWordCount;


public class OldWordCountTest extends TestCase {
  private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
  private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
  private Mapper<LongWritable, Text, Text, IntWritable> mapper;
  private Reducer<Text, IntWritable, Text, IntWritable> reducer;

  private static final LongWritable one = new LongWritable(1);

  @Override
  @Before
  public void setUp() {
    mapper = new OldWordCount.Map();
    reducer = new OldWordCount.Reduce();
    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>(
        mapper);
    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(
        reducer);
    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>(
        mapper, reducer);
  }

  @Test
  public void testMapper() {
    mapDriver.withInput(one, new Text("one two three")).withOutput(
        new Text("one"), new IntWritable(1)).withOutput(
        new Text("two"), new IntWritable(1)).withOutput(
        new Text("three"), new IntWritable(1)).runTest();
  }

  @Test
  public void testReducer() {
    List<IntWritable> counts = Arrays.asList(new IntWritable(3),
        new IntWritable(4));
    reduceDriver.withInput(new Text("one"), counts).withOutput(
        new Text("one"), new IntWritable(7)).runTest();
  }

  @Test
  public void testMapReduce() {
    mapReduceDriver.withInput(one, new Text("b c a b c c")).withOutput(
        new Text("a"), new IntWritable(1)).withOutput(new Text("b"),
        new IntWritable(2)).withOutput(new Text("c"),
        new IntWritable(3)).runTest();
  }

}
