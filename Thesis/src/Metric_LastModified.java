import java.io.File;
import java.util.Date;


public class Metric_LastModified implements Metric_Abstract {

	public long getMetricValue(File file) {
		return file.lastModified();
	}
	
	public String getMetricString(long metricValue) {
		return new Date(metricValue).toString();
	}
}
