import java.io.File;

/**
 * GDM_Controller
 * The controller portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_Controller {
	
	// The gdm_view object
	private GDM_View gdm_view;
	
	// The gdm_model object
	private GDM_Model gdm_model;
	
	// The metric being used
	private Metric_Abstract metricType;
	
	public static void main(String[] args)
	{
		new GDM_Controller();
	}
	
	/**
	 * GDM_Controller()
	 * The constructor for the GDM_Controller object.
	 */
	public GDM_Controller()
	{
		metricType = new Metric_Size();
		gdm_model = new GDM_Model(this);
		gdm_view = new GDM_View(this, gdm_model);
		while (! initializeDirectory());
		exit();
	}
	
	public boolean initializeDirectory() {
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
			gdm_model.initializeDirectory(directory, metricType);
			return true;
		}
		return false;
	}
	
	public void exit()
	{
		System.exit(0);
	}
}
