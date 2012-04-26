import java.io.File;
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
	
	private File toplevel_directory;
	
	private Tree_Component fileStruct;
	
	/**
	 * GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model(GDM_Controller gdm_controller)
	{
		this.gdm_controller = gdm_controller;
	}
	
	public void initializeDirectory(File directory, Metric_Abstract metricType)
	{
		fileStruct = new Tree_Base(directory, metricType);
	}
	
	public String getDataString()
	{
		return getDirectoryData(toplevel_directory, "");
	}
	
	public String getDirectoryData(File directory, String indent)
	{
		String dataString = indent + directory.getName() +
				            " - " + directory.length() + " bytes\n";
		indent += "  ";
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			if (files[i].isDirectory())
			{
				dataString += getDirectoryData(files[i], indent);
			}
			else
			{
				dataString += indent + files[i].getName() +
						      " - " + files[i].length() + " bytes\n";
			}
		}
		return dataString;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
