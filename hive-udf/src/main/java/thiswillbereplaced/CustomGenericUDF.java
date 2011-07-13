package thiswillbereplaced;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFLocate;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * Copy of {@link GenericUDFLocate}
 * 
 * Generic UDF for string function <code>LOCATE(substr, str)</code>,
 * <code>LOCATE(substr, str, start)</code>. This mimcs the function from MySQL
 * http://dev.mysql.com/doc/refman/5.1/en/string-functions.html#function_locate
 * 
 * <pre>
 * usage:
 * LOCATE(substr, str)
 * LOCATE(substr, str, start)
 * </pre>
 * <p>
 */
@Description(name = "locate", value = "_FUNC_(substr, str[, pos]) - Returns the position of the first "
  + "occurance of substr in str after position pos", extended = "Example:\n"
  + "  > SELECT _FUNC_('bar', 'foobarbar', 5) FROM src LIMIT 1;\n" + "  7")
public class CustomGenericUDF extends GenericUDF {
  private ObjectInspectorConverters.Converter[] converters;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
    throws UDFArgumentException {
    if (arguments.length != 2 && arguments.length != 3) {
      throw new UDFArgumentLengthException(
        "The function LOCATE accepts exactly 2 or 3 arguments.");
    }

    for (int i = 0; i < arguments.length; i++) {
      Category category = arguments[i].getCategory();
      if (category != Category.PRIMITIVE) {
        throw new UDFArgumentTypeException(i, "The "
          + GenericUDFUtils.getOrdinal(i + 1)
          + " argument of function LOCATE is expected to a "
          + Category.PRIMITIVE.toString().toLowerCase() + " type, but "
          + category.toString().toLowerCase() + " is found");
      }
    }

    converters = new ObjectInspectorConverters.Converter[arguments.length];
    for (int i = 0; i < arguments.length; i++) {
      if (i == 0 || i == 1) {
        converters[i] = ObjectInspectorConverters.getConverter(arguments[i],
          PrimitiveObjectInspectorFactory.writableStringObjectInspector);
      } else if (i == 2) {
        converters[i] = ObjectInspectorConverters.getConverter(arguments[i],
          PrimitiveObjectInspectorFactory.writableIntObjectInspector);
      }
    }

    return PrimitiveObjectInspectorFactory.writableIntObjectInspector;
  }

  private final IntWritable intWritable = new IntWritable(0);

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (arguments[0].get() == null || arguments[1].get() == null) {
      return null;
    }

    Text subtext = (Text) converters[0].convert(arguments[0].get());
    Text text = (Text) converters[1].convert(arguments[1].get());
    int start = 1;
    if (arguments.length == 3) {
      IntWritable startWritable = (IntWritable) converters[2].convert(arguments[2].get());
      if (startWritable == null) {
        intWritable.set(0);
        return intWritable;
      }
      start = startWritable.get();
    }
    intWritable.set(GenericUDFUtils.findText(text, subtext, start - 1) + 1);
    return intWritable;
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length == 2 || children.length == 3);
    return "locate(" + children[0] + children[1]
      + (children.length == 3 ? children[2] : "") + ")";
  }
}
