import java.io.File;


public class Metric_Size implements Metric_Abstract {

	public long getMetricValue(File file) {
		return file.length();
	}
	
	public String getMetricString(long metricValue) {
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
