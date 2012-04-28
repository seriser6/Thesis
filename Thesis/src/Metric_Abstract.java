import java.io.File;
import java.util.Iterator;


public abstract class Metric_Abstract {
	
	public abstract long getMetricValue(File file);
	
	public long getMetricValue(Tree_Directory directory) {
		long total = 0;
		Iterator<Tree_Component> iterator = directory.getIterator();
		while(iterator.hasNext()) {
			total += iterator.next().getMetricValue();
		}
		return total;
	}

	public long getMetricValue(Tree_File file) {
		return getMetricValue(new File(file.getPath()));
	}
}
