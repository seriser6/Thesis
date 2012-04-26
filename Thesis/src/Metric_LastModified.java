import java.io.File;


public class Metric_LastModified extends Metric_Abstract {

	@Override
	public long getMetricValue(File file) {
		return file.lastModified();
	}
}
