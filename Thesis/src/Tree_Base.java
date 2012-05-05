import java.io.File;


public class Tree_Base extends Tree_Directory {
	private String parentPath;
	
	public Tree_Base(File file, Metric_Abstract metricType) {
		super(file, null, metricType);
		parentPath = file.getParent();
		if (parentPath == null) {
			parentPath = "";
		}
		else {
			parentPath = parentPath + File.separator;
		}
	}

	@Override
	public String getParentPath() {
		return parentPath;
	}
}
