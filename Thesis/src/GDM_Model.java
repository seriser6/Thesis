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
	private GDM_Controller gdm_controller;
	
	private Tree_Base toplevel_directory;
	
	private Tree_Directory fileStruct;
	
	private Metric_Abstract metricType;
	
	private boolean stable;
	
	/**
	 * GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model(GDM_Controller gdm_controller)
	{
		this.gdm_controller = gdm_controller;
		stable = false;
	}
	
	public void setMetricType(Metric_Abstract metricType) {
		this.metricType = metricType;
	}
	
	public boolean isStable() {
		return stable;
	}
	
	public void initializeDirectory(File directory, GDM_View view)
	{
		stable = false;
		initializeFileStruct(directory);
		initializeRectangleSizes(view);
		stable = true;
	}
	
	public void initializeFileStruct(File directory) {
		toplevel_directory = new Tree_Base(directory, metricType);
	}
	
	public void initializeRectangleSizes(GDM_View view) {
		stable = false;
		int sizeX = view.getCanvasWidth();
		int sizeY = view.getCanvasHeight();
		
		toplevel_directory.makeRectangleBase(sizeX, sizeY);
		
		fileStruct = toplevel_directory;
		stable = true;
	}
	
	public String getFileDescription(int x, int y) {
		return getFileDescription(x, y, fileStruct);
	}
	
	public String getFileDescription(int x, int y, Tree_Directory directory) {
		//System.out.println(x + " " + y);
		Iterator<Tree_Component> iter = directory.getIterator();
		while (iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (x >= treeComponent.getPositionX() &&
				x <= treeComponent.getPositionX() + treeComponent.getSizeX() &&
				y >= treeComponent.getPositionY() &&
				y <= treeComponent.getPositionY() + treeComponent.getSizeY()) {
				if (treeComponent.isDirectory()) {
					return getFileDescription(x, y, (Tree_Directory) treeComponent);
				}
				else {
					return treeComponent.getPath() + "   |   " + metricType.getMetricString(treeComponent.getMetricValue());
				}
			}
		}
		return "";
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
