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
	implements Runnable
{

	// The GDM_Controller object
	private GDM_Controller gdm_controller;
	
	private Tree_Base toplevel_directory;
	
	private Tree_Directory fileStruct;
	
	/**
	 * GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model(GDM_Controller gdm_controller)
	{
		this.gdm_controller = gdm_controller;
	}
	
	public void initializeDirectory(File directory, Metric_Abstract metricType, GDM_View view)
	{
		toplevel_directory = new Tree_Base(directory, metricType);
		
		toplevel_directory.makeDimension();
		
		int sizeX = view.getCanvasWidth();
		int sizeY = view.getCanvasHeight();
		
		toplevel_directory.makeRectangleBase(sizeX, sizeY);
		
		fileStruct = toplevel_directory;
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
