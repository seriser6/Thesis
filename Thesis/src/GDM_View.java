import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JPanel panel_graphic;
	private JLabel label_text;
	
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
		frame.setPreferredSize(new Dimension(1900, 1000));
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
		
		panel_graphic = new JPanel();
		frame.getContentPane().add(panel_graphic, BorderLayout.CENTER);
		
		panel_text = new JPanel();
		frame.getContentPane().add(panel_text, BorderLayout.SOUTH);
		
		label_text = new JLabel("Bottom Label");
		label_text.setHorizontalAlignment(SwingConstants.LEFT);
		panel_text.add(label_text);
		
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
		label_text.setText("Error: File chosen was not a directory");
	}
	
	public void error_noFileChosen()
	{
		label_text.setText("Error: No file was chosen");
	}
	
	public int getCanvasWidth() {
		return panel_graphic.getWidth()-1;
	}
	public int getCanvasHeight() {
		return panel_graphic.getHeight()-1;
	}
	
	public void repaint() {
		paint(panel_graphic.getGraphics());
	}
	
	public void paint(Graphics g) {
		drawRectangle(g, gdm_model.getTreeDirectory());
	}
	
	public void drawRectangle(Graphics g, Tree_Directory directory) {
		g.drawRect(directory.getPositionX(), directory.getPositionY(), directory.getSizeX(), directory.getSizeY());
		
		Iterator<Tree_Component> iter = directory.getIterator();
		while(iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (treeComponent.isDirectory()) {
				drawRectangle(g, (Tree_Directory) treeComponent);
			}
			else {
				g.drawRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
			}
		}
	}
}
