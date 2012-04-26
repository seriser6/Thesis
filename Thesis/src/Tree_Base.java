import java.io.File;


public class Tree_Base extends Tree_Directory {
	private String parentPath;
	
	public Tree_Base(File file, Metric_Abstract metricType) {
		super(file, null, metricType);
		parentPath = file.getParent();
	}

	@Override
	public String getParentPath() {
		return parentPath;
	}

}
