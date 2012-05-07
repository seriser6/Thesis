import java.awt.Color;
import java.io.File;

/**
 * Tree_File
 * A class representing a leaf node of the file tree structure
 * extends Tree_Component so the composite pattern can be used
 */
public class Tree_File extends Tree_Component 
{
	
	private Color color;
	
	/**
	 * public Tree_File(File file, Tree_Directory parent, Metric_Abstract metricType)
	 * constructor for a Tree_File
	 * @param file - the file to construct this node with
	 * @param parent - the parent of this node
	 * @param metricType - the metric to be used for this file
	 */
	public Tree_File(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		setMetricValue(metricType, file);
	}
	
	/**
	 * public Tree_File(Tree_Directory directory) 
	 * constructor for a Tree_File that allows for conversion from directory to file
	 * @param directory - the directory to turn into a file
	 */
	public Tree_File(Tree_Directory directory) 
	{
		super(new File(directory.getPath()),directory.getParent());
		setMetricValue(directory.getMetricValue());
	}

	/**
	 * private void setMetricValue(Metric_Abstract metricType, File file)
	 * sets the metric value
	 * @param metricType - the metic being used
	 * @param file - the file to use as the basis for the metric
	 */
	private void setMetricValue(Metric_Abstract metricType, File file)
	{
		setMetricValue(metricType.getMetricValue(file));
	}

	/**
	 * public Color getColor()
	 * getter for color
	 * @return color - the color for this node
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType)
	 * sets the values pertaining to the node's rectangular representation
	 * @param sizeX - the horizontal size of the rectangle
	 * @param sizeY - the vertical size of the rectangle
	 * @param posX - the x position of the rectangle
	 * @param posY - the y position of the rectangle
	 * @param horizontal - not used, intended for directories
	 * @param colorType - the color type for color generation
	 * @see Tree_Component#makeRectangle(int, int, int, int, boolean, Color_Abstract)
	 */
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) 
	{
		super.makeRectangle(sizeX, sizeY, posX, posY, horizontal, colorType);
		color = colorType.getColor(this);
	}
	
	/**
	 * public void activate()
	 * sets active to true
	 * @see Tree_Component#activate()
	 */
	public void activate() {
		setActive(true);
	}
	
	/**
	 * public void deactive
	 * sets active to false
	 * @see Tree_Component#deactivate()
	 */
	public void deactivate() {
		setActive(false);
	}
}
