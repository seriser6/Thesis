import java.io.File;
import java.util.Iterator;

/**
 * GDM_Model
 * The model portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_Model
{
	// the base of the Tree_Component file structure
	private Tree_Base fileStruct;
	
	// a boolean indicating if the structure is stable or updating
	private boolean stable;
	
	/**
	 * public GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model()
	{
		stable = false;
	}
	
	/**
	 * public boolean isStable()
	 * returns the value of stable
	 * @return stable
	 */
	public boolean isStable() 
	{
		return stable;
	}
	
	/**
	 * public void refreshDirectory(GDM_View view)
	 * reinitializes filestruct at the current directory
	 * @param view - the GDM_View object
	 */
	public void refreshDirectory(GDM_View view) 
	{
		stable = false;
		initializeDirectory(new File(fileStruct.getPath()), view);
		stable = true;
	}
	
	/**
	 * public void changeDirectoryUp(GDM_View view)
	 * changes the fileStruct to have a root node at the parent of the current root
	 * @param view - the GDM_View object
	 */
	public void changeDirectoryUp(GDM_View view) 
	{
		stable = false;
		initializeDirectory(new File(fileStruct.getParentPath()), view);
		stable = true;
	}
	
	/**
	 * public void changeDirectoryDown(int x, int y, GDM_View view)
	 * changes the fileStruct to have a root node at the selected child node
	 * @param x - the x coordinate of the mouse click
	 * @param y - the y coordinate of the mouse click
	 * @param view - the GDM_View object
	 */
	public void changeDirectoryDown(int x, int y, GDM_View view) 
	{
		stable = false;
		Iterator<Tree_Component> iter = fileStruct.getIterator();
		while (iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (x >= treeComponent.getPositionX() &&
				x <= treeComponent.getPositionX() + treeComponent.getSizeX() &&
				y >= treeComponent.getPositionY() &&
				y <= treeComponent.getPositionY() + treeComponent.getSizeY()) {
				treeComponent.activate();
				if (treeComponent.isDirectory()) {
					initializeDirectory(new File(treeComponent.getPath()), view);
				}
				break;
			}
		}
		stable = true;
	}
	
	/**
	 * public void initializeDirectory(File directory, GDM_View view)
	 * initializes the file structure and its rectangle data at the given directory
	 * @param directory - the directory to initialize to
	 * @param view - the GDM_View object
	 */
	public void initializeDirectory(File directory, GDM_View view)
	{
		stable = false;
		initializeFileStruct(directory, view.getMetricType());
		initializeRectangleSizes(view.getCanvasWidth(), view.getCanvasHeight(), view.getColorType());
		stable = true;
	}
	
	/**
	 * private void initializeFileStruct(File directory, Metric_Abstract metricType)
	 * initializes the file structure by constructing the tree base with the given directory
	 * @param directory - the directory to initialize the structure at
	 * @param metricType - the metric type to use
	 */
	private void initializeFileStruct(File directory, Metric_Abstract metricType) 
	{
		fileStruct = null;
		fileStruct = new Tree_Base(directory, metricType);
	}
	
	/**
	 * public void initializeRectangleSizes(int canvasX, int canvasY, Color_Abstract colorType) 
	 * initializes the rectangular node sizes of the file structure
	 * @param canvasX - the horizontal size of the graphic panel
	 * @param canvasY - the vertical size of the graphic panel
	 * @param colorType - the color algorithm to use
	 */
	public void initializeRectangleSizes(int canvasX, int canvasY, Color_Abstract colorType) 
	{
		stable = false;
		fileStruct.makeRectangleBase(canvasX, canvasY, colorType);	
		stable = true;
	}
	
	/**
	 * public String getFileDescription(int x, int y, Metric_Abstract metricType) 
	 * get the description of the file node at the given coordinates
	 * @param x - the x coordinate of the mouse
	 * @param y - the y coordinate of the mouse
	 * @param metricType - the metric algorithm to use
	 * @return the file description
	 */
	public String getFileDescription(int x, int y, Metric_Abstract metricType) 
	{
		return getFileDescription(x, y, metricType, fileStruct);
	}
	
	/**
	 * private String getFileDescription(int x, int y, Metric_Abstract metricType, Tree_Directory directory) 
	 * recursively works its way down the file tree to obtain the description of the correct file
	 * @param x - the x coordinate of the mouse
	 * @param y - the y coordinate of the mouse
	 * @param metricType - the metric algorithm to use
	 * @param directory - the current directory we are looking at
	 * @return the file description
	 */
	private String getFileDescription(int x, int y, Metric_Abstract metricType, Tree_Directory directory) 
	{
		String returnString = "";
		Iterator<Tree_Component> iter = directory.getIterator();
		while (iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (x >= treeComponent.getPositionX() &&
				x <= treeComponent.getPositionX() + treeComponent.getSizeX() &&
				y >= treeComponent.getPositionY() &&
				y <= treeComponent.getPositionY() + treeComponent.getSizeY()) {
				treeComponent.activate();
				if (treeComponent.isDirectory()) {
					returnString = getFileDescription(x, y, metricType, (Tree_Directory) treeComponent);
				}
				else {
					returnString =  treeComponent.getPath() + "   |   " + metricType.getMetricString(treeComponent.getMetricValue());
				}
				break;
			}
			else {
				treeComponent.deactivate();
			}
		}
		while (iter.hasNext()) {
			iter.next().deactivate();
		}
		return returnString;
	}
	
	/**
	 * public Tree_Directory getTreeDirectory() 
	 * gets the base of the current file structure
	 * @return the Tree_Directory object representing that base
	 */
	public Tree_Directory getTreeDirectory() 
	{
		return fileStruct;
	}
	
	/**
	 * public String getDataString()
	 * returns a string representation of the entire file tree
	 * @return the String consisting of the file tree's data
	 */
	public String getDataString()
	{
		return getDataString(fileStruct, "");
	}
	
	/**
	 * private String getDataString(Tree_Directory directory, String indent)
	 * recursively returns a string representation of the files in a given directory
	 * @param directory - the current directory
	 * @param indent - the current indentation factor
	 * @return the String consisting of the file tree's data
	 */
	private String getDataString(Tree_Directory directory, String indent)
	{
		String dataString = indent + directory.getName() + " - " + 
							directory.getMetricValue() + "\n";
		
		indent += "  ";
		
		Iterator<Tree_Component> iter = directory.getIterator();
		
		while (iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (treeComponent.isDirectory()) {
				dataString += getDataString((Tree_Directory) treeComponent, indent);
			}
			else {
				dataString += indent + treeComponent.getName() + " - " + 
							  treeComponent.getMetricValue() + "\n";
			}
		}
		
		return dataString;
	}
}
