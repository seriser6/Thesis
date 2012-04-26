import java.io.File;


public abstract class Metric_Abstract {
	
	public abstract long getMetricValue(File file);
	
	public long getMetricValue(Tree_Directory directory) {
		return getMetricValue(new File(directory.getPath()));
	}

	public long getMetricValue(Tree_File file) {
		return getMetricValue(new File(file.getPath()));
	}
}
