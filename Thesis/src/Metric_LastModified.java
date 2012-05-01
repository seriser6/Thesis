import java.io.File;
import java.util.Date;


public class Metric_LastModified extends Metric_Abstract {

	@Override
	public long getMetricValue(File file) {
		return file.lastModified();
	}
	
	@Override
	public String getMetricString(long metricValue) {
		return new Date(metricValue).toString();
	}
}
