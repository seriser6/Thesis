import java.io.File;
import java.util.Date;

/**
 * Metric_OldestModified
 * implements Metric_Abstract in order to be used as a metric defining algorithm
 * sets metric values to the age of the last file modification
 */
public class Metric_OldestModified implements Metric_Abstract 
{

	// the current system time, for reference
	private long currentTime;
	
	/**
	 * public Metric_OldestModified()
	 * constructor, which is only used to store a persistent current system time
	 */
	public Metric_OldestModified() 
	{
		currentTime = System.currentTimeMillis();
	}
	
	/**
	 * public long getMetricValue(File file)
	 * returns the age of last modification that corresponds to the given file node
	 * @param file - the file to get a value for
	 * @return the value of the metric
	 */
	public long getMetricValue(File file) 
	{
		long metric = currentTime-file.lastModified();
		if (metric > 0)
			return currentTime-file.lastModified();
		else return 0;
	}

	/**
	 * public String getMetricString(long metricValue)
	 * returns a String representation of the given age of the last modification
	 * @param metricValue - the value to convert to a String
	 * @return a String representation of the metric's value
	 */
	public String getMetricString(long metricValue) 
	{
		return new Date(metricValue+currentTime).toString();
	}

}
