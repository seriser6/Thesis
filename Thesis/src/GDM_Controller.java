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
		gdm_view = new GDM_View();
		gdm_model = new GDM_Model();
	}
}
