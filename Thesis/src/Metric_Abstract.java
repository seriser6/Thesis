import java.io.File;


public interface Metric_Abstract {
	
	public long getMetricValue(File file);
	
	public String getMetricString(long metricValue);
}
