import java.io.File;

import javax.swing.JFileChooser;

/**
 * GDM_View
 * The view portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_View {

	// The GDM_Controller object
	private GDM_Controller gdm_controller;
	
	// The GDM_Model object
	private GDM_Model 	   gdm_model;
	
	/**
	 * GDM_View()
	 * The constructor for the GDM_View object.
	 */
	public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	{
		this.gdm_controller = gdm_controller;
		this.gdm_model = gdm_model;
		printWelcomeMessage();
	}

	private void printWelcomeMessage()
	{
		System.out.println("Welcome to John's Graphical Disk Map Thesis.");
	}
	
	public File chooseDirectory()
	{
		File directory = null;
		int  chooserReturnValue;
		System.out.println("Choose a Directory");
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooserReturnValue = jFileChooser.showOpenDialog(null);
		if (chooserReturnValue == JFileChooser.APPROVE_OPTION)
		{
			directory = jFileChooser.getSelectedFile();
		}
		return directory;
	}
	
	public void modelChanged()
	{
		System.out.println(gdm_model.getDataString());
	}
	
	public void error_notDirectory()
	{
		System.out.println("Error: File chosen was not a directory");
	}
	
	public void error_noFileChosen()
	{
		System.out.println("Error: No file was chosen");
	}
}
