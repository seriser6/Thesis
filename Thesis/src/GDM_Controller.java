import java.io.File;

/**
 * GDM_Controller
 * The controller portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_Controller 
{
	
	// The GDM_View object
	private GDM_View gdm_view;
	
	// The GDM_Model object
	private GDM_Model gdm_model;
	
	public static void main(String[] args)
	{
		new GDM_Controller();
	}
	
	/**
	 * public GDM_Controller()
	 * The constructor for the GDM_Controller object.
	 */
	public GDM_Controller()
	{
		gdm_model = new GDM_Model();
		gdm_view = new GDM_View(this, gdm_model);
		initializeDirectory();
		while (true);
	}
	/**
	 * public void initializeDirectory()
	 * initializes the program to the a directory chosen by the user
	 */
	public void initializeDirectory() 
	{
		File directory = gdm_view.chooseDirectory();
		if (directory == null)
		{
			gdm_view.error_noFileChosen();
		}
		else if (!directory.isDirectory())
		{
			gdm_view.error_notDirectory();
		}
		else
		{
			gdm_model.initializeDirectory(directory, gdm_view);
			gdm_view.graphicsChanged();
		}
	}
}
