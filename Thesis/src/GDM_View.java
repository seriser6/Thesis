import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.ScrollPaneConstants;

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
	
	// The GUI components
	private JFrame frame;
	private JPanel panel_text;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JPanel panel_buttons;
	
	/**
	 * GDM_View()
	 * The constructor for the GDM_View object.
	 */
	public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	{
		this.gdm_controller = gdm_controller;
		this.gdm_model = gdm_model;
		printWelcomeMessage();
		createGUI();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void createGUI()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 300));
		frame.setVisible(true);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		panel_buttons = new JPanel();
		frame.getContentPane().add(panel_buttons, BorderLayout.NORTH);
		panel_buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnNewButton = new JButton("New button");
		panel_buttons.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		panel_buttons.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("New button");
		panel_buttons.add(btnNewButton_2);
		
		JPanel panel_graphic = new JPanel();
		frame.getContentPane().add(panel_graphic, BorderLayout.CENTER);
		
		panel_text = new JPanel();
		frame.getContentPane().add(panel_text, BorderLayout.SOUTH);
		
		frame.pack();
	}

	private void printWelcomeMessage()
	{
		//System.out.println("Welcome to John's Graphical Disk Map Thesis.");
	}
	
	public File chooseDirectory()
	{
		File directory = null;
		int  chooserReturnValue;
		//System.out.println("Choose a Directory");
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooserReturnValue = jFileChooser.showOpenDialog(null);
		if (chooserReturnValue == JFileChooser.APPROVE_OPTION)
		{
			directory = jFileChooser.getSelectedFile();
		}
		return directory;
	}
	
	public void error_notDirectory()
	{
		textArea.setText("Error: File chosen was not a directory");
	}
	
	public void error_noFileChosen()
	{
		textArea.setText("Error: No file was chosen");
	}
}
