import java.io.File;
import java.util.Iterator;


public class Metric_Size extends Metric_Abstract {

	@Override
	public long getMetricValue(File file) {
		return file.length();
	}
	
	@Override
	public long getMetricValue(Tree_Directory directory) {
		long total = 0;
		Iterator<Tree_Component> iterator = directory.getIterator();
		while(iterator.hasNext()) {
			total += iterator.next().getMetricValue();
		}
		return total;
	}
}
