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
	
	public void setMetricValue(Metric_Abstract metricType)
	{
		Iterator<Tree_Component> iter = getIterator();
		metricValue += iter.next().getMetricValue();
	}
	
	public Iterator<Tree_Component> getIterator()
	{
		return subFiles.iterator();
	}
	
	public void makeDimension(int dimension)
	{
		this.dimension = dimension;

		Iterator<Tree_Component> iter = getIterator();
		while (iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			treeComponent.makeDimension((int) (10000 * treeComponent.getMetricValue() / metricValue));
		}
	}
	
	public void makeDimension()
	{
		makeDimension(10000);
	}
	
	public void makeRectangleBase(int sizeX, int sizeY)
	{
		makeRectangle(sizeX, sizeY, 0, 0, true);
	}
	
	// horizontal if horizontal is true, else vertical
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal)
	{
		super.makeRectangle(sizeX,sizeY,posX,posY,horizontal);
		Iterator<Tree_Component> iter = getIterator();
		
		
		int childSize;
		if (horizontal) {
			while (iter.hasNext()) {
				Tree_Component treeComponent = iter.next();
				childSize = sizeX * treeComponent.getDimension() / dimension;
				treeComponent.makeRectangle(childSize, sizeY, posX, posY, false);
				posX += childSize;
			}
		}
		else {
			while (iter.hasNext()) {
				Tree_Component treeComponent = iter.next();
				childSize = sizeY * treeComponent.getDimension() / dimension;
				treeComponent.makeRectangle(sizeX, childSize, posX, posY, true);
				posY += childSize;
			}
		}
	}
}