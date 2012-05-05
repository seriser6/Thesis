import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Tree_Directory extends Tree_Component {

	private ArrayList<Tree_Component> subFiles;

	public Tree_Directory(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		File[] fileArray = file.listFiles();
		
		if (fileArray != null) {
			setDirectory(true);
			int fileCount = fileArray.length;
			subFiles = new ArrayList<Tree_Component>(fileCount);
			
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
				
				setMetricValue(getMetricValue() + newFile.getMetricValue());
				subFiles.add(newFile);
			}
		}
	}
	
	public Iterator<Tree_Component> getIterator()
	{
		return subFiles.iterator();
	}
	
	public void makeRectangleBase(int sizeX, int sizeY, Color_Abstract colorType)
	{
		makeRectangle(sizeX, sizeY, 0, 0, true, colorType);
	}
	
	// horizontal if horizontal is true, else vertical
	@Override
	protected void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType)
	{
		super.makeRectangle(sizeX,sizeY,posX,posY,horizontal, colorType);
		if (isDirectory()) {
			Iterator<Tree_Component> iter = getIterator();
			
			int childSize;
			if (horizontal) {
				while (iter.hasNext()) {
					Tree_Component treeComponent = iter.next();
					try {
						//childSize = (int)Math.ceil((double)sizeX * (double)treeComponent.getMetricValue() / (double)metricValue);
						childSize = (int)Math.round((double)sizeX * (double)treeComponent.getMetricValue() / (double)getMetricValue());
						//childSize = (int) (sizeX * treeComponent.getMetricValue() / metricValue);
					}
					catch (ArithmeticException e) {
						childSize = 0;
					}
					treeComponent.makeRectangle(childSize, sizeY, posX, posY, false, colorType);
					posX += childSize;
				}
			}
			else {
				while (iter.hasNext()) {
					Tree_Component treeComponent = iter.next();
					try {
						//childSize = (int)Math.ceil((double)sizeY * (double)treeComponent.getMetricValue() / (double)metricValue);
						childSize = (int)Math.round((double)sizeY * (double)treeComponent.getMetricValue() / (double)getMetricValue());
						//childSize = (int) (sizeY * treeComponent.getMetricValue() / metricValue);
					}
					catch (ArithmeticException e) {
						childSize = 0;
					}
					treeComponent.makeRectangle(sizeX, childSize, posX, posY, true, colorType);
					posY += childSize;
				}
			}
		}
	}
	
	public void deactivate() {
		if (isActive()) {
			setActive(false);
			Iterator<Tree_Component> iter = getIterator();
			while (iter.hasNext()) {
				iter.next().deactivate();
			}
		}
	}
	
	public void activate() {
		setActive(true);
	}
}
