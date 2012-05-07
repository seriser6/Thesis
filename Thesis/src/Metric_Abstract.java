import java.io.File;

/**
 * Metric_Abstract
 * An interface used by the view, representing a node size metric algorithm
 */
public interface Metric_Abstract 
{
	
	/**
	 * public long getMetricValue(File file)
	 * returns the value of the metric that corresponds to the given file node
	 * @param file - the file to get a value for
	 * @return the value of the metric
	 */
	public long getMetricValue(File file);
	
	/**
	 * public String getMetricString(long metricValue)
	 * returns a String representation of the given value
	 * @param metricValue - the value to convert to a String
	 * @return a String representation of the metric's value
	 */
	public String getMetricString(long metricValue);
}
