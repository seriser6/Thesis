import java.io.File;

/**
 * Metric_Size
 * implements Metric_Abstract in order to be used as a metric defining algorithm
 * sets metric values to the file size
 */
public class Metric_Size implements Metric_Abstract 
{

	/**
	 * public long getMetricValue(File file)
	 * returns file size that corresponds to the given file node
	 * @param - file the file to get a value for
	 * @return the value of the metric
	 */
	public long getMetricValue(File file) 
	{
		return file.length();
	}
	
	/**
	 * public String getMetricString(long metricValue)
	 * returns a String representation of the file size
	 * @param metricValue - the value to convert to a String
	 * @return a String representation of the metric's value
	 */
	public String getMetricString(long metricValue) 
	{
		double value = (double) metricValue;
		String unit;
		if (value > 1000000000) {
			value = ((int) value / 1000000) / 1000.0;
			unit = " gigabytes";
		}
		else if (value > 1000000) {
			value = ((int) value / 1000) / 1000.0;
			unit = " megabytes";
		}
		else if (value > 1000) {
			value = value / 1000.0;
			unit = " kilobytes";
		}
		else {
			unit = " bytes";
		}
		return value + unit;
	}
}
