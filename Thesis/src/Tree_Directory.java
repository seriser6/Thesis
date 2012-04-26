import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Tree_Directory extends Tree_Component {

	ArrayList<Tree_Component> subFiles;
	
	public Tree_Directory(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		File[] fileArray = file.listFiles();
		int fileCount = fileArray.length;
		subFiles = new ArrayList<Tree_Component>(fileCount+2);
		
		for (int i = 0; i < fileCount; i++)
		{
			Tree_Component newFile;
			
			if (fileArray[i].isDirectory())
			{
				newFile = new Tree_Directory(fileArray[i], this, metricType);
			}
			else
			{
				newFile = new Tree_File(fileArray[i], this, metricType);
			}
			
			metricValue += newFile.getMetricValue();
			subFiles.add(newFile);
		}
	}
	
	public void setMetricValue(Metric_Abstract metricType, File file)
	{
		for (int i = 0; i < subFiles.size(); i++)
		{
		}
	}
	
	public Iterator<Tree_Component> getIterator()
	{
		return subFiles.iterator();
	}
}
