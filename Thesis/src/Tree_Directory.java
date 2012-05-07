import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tree_Directory
 * A class representing a non-leaf node of the file tree structure
 * extends Tree_Component so the composite pattern can be used
 */
public class Tree_Directory extends Tree_Component 
{
	// the child nodes of this directory
	private ArrayList<Tree_Component> subFiles;

	/**
	 * public Tree_Directory(File file, Tree_Directory parent, Metric_Abstract metricType)
	 * constructor for a Tree_Directory, constructs the directory's children as well
	 * @param file - the File object to turn into a node
	 * @param parent - the parent node of this node
	 * @param metricType - the metric type to use for this node's rectangle
	 */
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
	
	/**
	 * public Iterator<Tree_Component> getIterator()
	 * returns an iterator over this node's children
	 * @return an iterator over this node's children
	 */
	public Iterator<Tree_Component> getIterator()
	{
		return subFiles.iterator();
	}
	
	
	
	/**
	 * public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType)
	 * sets the values pertaining to the node's rectangular representation, and defines subnodes as well
	 * @param sizeX - the horizontal size of the rectangle
	 * @param sizeY - the vertical size of the rectangle
	 * @param posX - the x position of the rectangle
	 * @param posY - the y position of the rectangle
	 * @param horizontal - whether this directory's children should be built horizontally or vertically
	 * @param colorType - the color type for color generation, passed to children
	 * @see Tree_Component#makeRectangle(int, int, int, int, boolean, Color_Abstract)
	 */
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
	
	/**
	 * public void deactivate() 
	 * sets active to false and deactivates all this node's children
	 * @see Tree_Component#deactivate()
	 */
	public void deactivate() 
	{
		if (isActive()) {
			setActive(false);
			Iterator<Tree_Component> iter = getIterator();
			while (iter.hasNext()) {
				iter.next().deactivate();
			}
		}
	}
	
	/**
	 * public void activate()
	 * sets active to true
	 * @see Tree_Component#activate()
	 */
	public void activate() 
	{
		setActive(true);
	}
}
