import java.io.File;
import java.util.Date;

/**
 * Metric_LastModified
 * implements Metric_Abstract in order to be used as a metric defining algorithm
 * sets metric values to the last modified time of the file
 */
public class Metric_LastModified implements Metric_Abstract 
{

	/**
	 * public long getMetricValue(File file)
	 * returns the last modified time that corresponds to the given file node
	 * @param file - the file to get a value for
	 * @return the value of the metric
	 */
	public long getMetricValue(File file) 
	{
		return file.lastModified();
	}
	
	/**
	 * public String getMetricString(long metricValue)
	 * returns a String representation of the given last modified time
	 * @param metricValue - the value to convert to a String
	 * @return a String representation of the metric's value
	 */
	public String getMetricString(long metricValue) 
	{
		return new Date(metricValue).toString();
	}
}
