import java.io.File;
import java.util.Date;


public class Metric_OldestModified implements Metric_Abstract {

	private long currentTime;
	
	public Metric_OldestModified() {
		currentTime = System.currentTimeMillis();
	}
	
	public long getMetricValue(File file) {
		long metric = currentTime-file.lastModified();
		if (metric > 0)
			return currentTime-file.lastModified();
		else return 0;
	}

	public String getMetricString(long metricValue) {
		return new Date(metricValue+currentTime).toString();
	}

}
