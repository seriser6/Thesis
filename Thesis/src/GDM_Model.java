import java.io.File;
import java.util.Iterator;
import java.util.Observable;

/**
 * GDM_Model
 * The model portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_Model 
	extends Observable
{

	// The GDM_Controller object
	//private GDM_Controller gdm_controller;
	
	private Tree_Base fileStruct;
	
	private boolean stable;
	
	private Metric_Abstract metricType;
	
	/**
	 * GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model(GDM_Controller gdm_controller)
	{
		//this.gdm_controller = gdm_controller;
		stable = false;
	}
	
	public boolean isStable() {
		return stable;
	}
	
	public void changeDirectoryUp(GDM_View view) {
		stable = false;
		initializeDirectory(new File(fileStruct.getParentPath()), view);
		stable = true;
	}
	
	public void changeDirectoryDown(int x, int y, GDM_View view) {
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
	
	public void initializeDirectory(File directory, GDM_View view)
	{
		stable = false;
		initializeFileStruct(directory, view.getMetricType());
		initializeRectangleSizes(view.getCanvasWidth(), view.getCanvasHeight(), view.getColorType());
		stable = true;
	}
	
	public void initializeFileStruct(File directory, Metric_Abstract metricType) {
		fileStruct = null;
		this.metricType = metricType;
		fileStruct = new Tree_Base(directory, metricType);
	}
	
	public void initializeRectangleSizes(int canvasX, int canvasY, Color_Abstract colorType) {
		stable = false;
		fileStruct.makeRectangleBase(canvasX, canvasY, colorType);	
		stable = true;
	}
	
	public String getFileDescription(int x, int y) {
		return getFileDescription(x, y, fileStruct);
	}
	
	public String getFileDescription(int x, int y, Tree_Directory directory) {
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
					returnString = getFileDescription(x, y, (Tree_Directory) treeComponent);
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
	
	public String getDataString()
	{
		return getDataString(fileStruct, "");
	}
	
	public String getDataString(Tree_Directory directory, String indent)
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
	
	public Tree_Directory getTreeDirectory() {
		return fileStruct;
	}
}
