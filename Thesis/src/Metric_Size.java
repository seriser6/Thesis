import java.io.File;


public class Metric_Size extends Metric_Abstract {

	@Override
	public long getMetricValue(File file) {
		return file.length();
	}
}
