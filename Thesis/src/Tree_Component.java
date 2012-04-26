import java.io.File;


public abstract class Tree_Component {
	protected String name;
	protected long metricValue;
	protected Tree_Directory parent;
	
	public Tree_Component(File file, Tree_Directory parent)
	{
		this.parent = parent;
		name = file.getName();
	}
	
	public long getMetricValue()
	{
		return metricValue;
	}
	
	public abstract void setMetricValue(Metric_Abstract metricType, File file);
	
	public void setMetricValue(Metric_Abstract metricType)
	{
		setMetricValue(metricType, new File(getPath()));
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getParentPath()
	{
		return parent.getPath();
	}
	
	public String getPath()
	{
		return getParentPath() + getName();
	}
}
