import java.io.File;


public class Tree_File extends Tree_Component {

	public Tree_File(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		setMetricValue(metricType, file);
	}

	public void setMetricValue(Metric_Abstract metricType, File file)
	{
		metricValue = metricType.getMetricValue(file);
	}
}
