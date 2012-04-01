import java.io.File;

/**
 * GDM_Model
 * The model portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_Model {

	// The GDM_Controller object
	private GDM_Controller gdm_controller;
	
	private File toplevel_directory;
	
	/**
	 * GDM_Model()
	 * The constructor for the GDM_Model object.
	 */
	public GDM_Model(GDM_Controller gdm_controller)
	{
		this.gdm_controller = gdm_controller;
	}
	
	public void modelChanged()
	{
		gdm_controller.modelChanged();
	}
	
	public void initializeDirectory(File directory)
	{
		this.toplevel_directory = directory;
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
}
