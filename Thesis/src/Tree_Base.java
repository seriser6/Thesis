import java.io.File;

/**
 * Tree_Base
 * A class representing the root node of the file tree structure
 * extends Tree_Directory (and thus Tree_Component) so the composite pattern can be used
 */
public class Tree_Base extends Tree_Directory 
{
	// the stored hard coded parent path
	private String parentPath;
	
	/**
	 * public Tree_Directory(File file, Tree_Directory parent, Metric_Abstract metricType)
	 * constructor for a Tree_Base (the root node of the structure), constructs the base's children as well
	 * @param file - the File object to turn into a node
	 * @param metricType - the metric type to use for this node's rectangle
	 */
	public Tree_Base(File file, Metric_Abstract metricType) 
	{
		super(file, null, metricType);
		parentPath = file.getParent();
		if (parentPath == null) {
			parentPath = "";
		}
		else {
			parentPath = parentPath + File.separator;
		}
	}

	/**
	 * public String getParentPath()
	 * returns the String representation of the parent of this file's path
	 * @return the parent's path
	 * @see Tree_Component#getParentPath()
	 */
	@Override
	public String getParentPath() 
	{
		return parentPath;
	}
	
	/**
	 * public void makeRectangleBase(int sizeX, int sizeY, Color_Abstract colorType)
	 * sets the rectangular values for a root node, with the largest overall rectangle
	 * @param sizeX - the horizontal size of the rectangle
	 * @param sizeY - the vertical size of the rectangle
	 * @param colorType - the color type for color generation, passed to children
	 */
	public void makeRectangleBase(int sizeX, int sizeY, Color_Abstract colorType)
	{
		makeRectangle(sizeX, sizeY, 0, 0, true, colorType);
	}
}
