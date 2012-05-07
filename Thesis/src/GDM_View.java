import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * GDM_View
 * The view portion of the model view controller.
 * @author John Sarris (sarriskj)
 */
public class GDM_View 
{

	// The GDM_Controller object
	private GDM_Controller gdm_controller;
	
	// The GDM_Model object
	private GDM_Model gdm_model;
	
	// The repaint timer, which 
	private Timer repaint_timer;
	
	// The two metrics that are currently set by the JComboBox
	private Metric_Abstract metricType;
	private Color_Abstract colorType;
	
	// A boolean that is true after the view has been drawn, used by the timer
	private boolean viewDrawn;
	
	// The GUI Swing components
	private JFrame frame;
	private JPanel panel_text;
	private JButton btn_OpenDirectory;
	private JPanel panel_buttons;
	private JPanel panel_graphic;
	private JLabel label_text;
	// In earlier versions of java, JComboBox does not take a type argument
	private JComboBox<String> comboBox_metric;
	private JComboBox<String> comboBox_color;
	private JButton btn_UpADirectory;
	private JButton btn_Refresh;
	
	/**
	 * public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	 * The constructor for the GDM_View object.
	 */
	public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	{
		this.gdm_controller = gdm_controller;
		this.gdm_model = gdm_model;
		viewDrawn = false;
		metricType = new Metric_Size();
		colorType = new Color_FileType();
		createGUI();
		repaint_timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modelStable() && viewDrawn()) {
					resetRectangles();
					graphicsChanged();
				}
			}
		});
		repaint_timer.start();
	}
	
	/**
	 * private void createGUI()
	 * A method that constructs and initializes the components of the GUI
	 * @wbp.parser.entryPoint
	 */
	private void createGUI()
	{
		// Create the JFrame
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(200, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1100,700));
		frame.setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		// Add the button panel
		panel_buttons = new JPanel(true);
		frame.getContentPane().add(panel_buttons, BorderLayout.NORTH);
		panel_buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Add the open directory button
		btn_OpenDirectory = new JButton("Open Directory");
		btn_OpenDirectory.addActionListener(new ActionListener() {
			// when button clicked, stop timer call initializeDirectory() in the controller
			public void actionPerformed(ActionEvent arg0) {
				repaint_timer.stop();
				gdm_controller.initializeDirectory();
			}
		});
		panel_buttons.add(btn_OpenDirectory);
		
		// Add the up a directory button
		btn_UpADirectory = new JButton("Up a Directory");
		btn_UpADirectory.addActionListener(new ActionListener() {
			// when button clicked, call changeDirectoryUp()
			public void actionPerformed(ActionEvent arg0) {
				changeDirectoryUp();
			}
		});
		panel_buttons.add(btn_UpADirectory);
		
		// Add the refresh button
		btn_Refresh = new JButton("Refresh");
		btn_Refresh.addActionListener(new ActionListener() {
			// when button clicked, call refreshDirectory()
			public void actionPerformed(ActionEvent arg0) {
				refreshDirectory();
			}
		});
		panel_buttons.add(btn_Refresh);
		
		// Add label for the size metric JComboBox
		JLabel lblMetric = new JLabel("Size Metric");
		panel_buttons.add(lblMetric);
		
		// Add the metric JComboBox
		comboBox_metric = new JComboBox<String>(new String[] {"File Size", "Last Modified", "Oldest Modified"});
		// In earlier versions of java, JComboBox does not take a type argument
		//comboBox_metric = new JComboBox(new String[] {"File Size", "Last Modified", "Oldest Modified"});
		comboBox_metric.setSelectedIndex(0);
		comboBox_metric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if new metric is selected, set that metric Type
				switch(comboBox_metric.getSelectedIndex()) {
				default:
				case 0:
					if (!(metricType instanceof Metric_Size)) {
						setMetricType(new Metric_Size());
					}
					break;
				case 1:
					if (!(metricType instanceof Metric_LastModified)) {
						setMetricType(new Metric_LastModified());
					}
					break;
				case 2:
					if (!(metricType instanceof Metric_OldestModified)) {
						setMetricType(new Metric_OldestModified());
					}
					break;
				}
			}
		});
		panel_buttons.add(comboBox_metric);
		
		// Add label for the color JComboBox
		JLabel lblColor = new JLabel("Coloring");
		panel_buttons.add(lblColor);
		
		comboBox_color = new JComboBox<String>(new String[] {"File Type", "Last Modified"});
		// In earlier versions of java, JComboBox does not take a type argument
		//comboBox_color = new JComboBox(new String[] {"File Type", "Last_Modified"});
		comboBox_color.setSelectedIndex(0);
		comboBox_color.addActionListener(new ActionListener() {
			// if new color is selected, set that color Type
			public void actionPerformed(ActionEvent e) {
				switch(comboBox_color.getSelectedIndex()) {
				default:
				case 0:
					if (!(colorType instanceof Color_FileType)) {
						setColorType(new Color_FileType());
					}	
					break;
				case 1:
					if (!(colorType instanceof Color_LastModified)) {
						setColorType(new Color_LastModified());
					}
					break;
				}
			}
		});
		panel_buttons.add(comboBox_color);
		
		// Add JPanel for performing graphics
		panel_graphic = new JPanel();
		panel_graphic.addMouseListener(new MouseAdapter() {
			// stop the repaint timer when the mouse leaves the graphical area
			// this is so the painting doesn't overwrite the JComboBox dropdown menu
			@Override
			public void mouseExited(MouseEvent arg0) {
				repaint_timer.stop();
			}
			// start the repaint timer when the mouse reenters the graphical area
			@Override
			public void mouseEntered(MouseEvent e) {
				repaint_timer.start();
			}
			// when a directory clicked, we want to change into that directory
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					changeDirectoryDown(arg0.getX(),arg0.getY());
				} catch(NullPointerException e) {
					// do nothing - phantom space or noninitialized graphics
				}
			}
		});
		panel_graphic.addMouseMotionListener(new MouseMotionAdapter() {
			// when we move the mouse, we want update the file name label
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					setFileDescription(arg0.getX(),arg0.getY());
				} catch(NullPointerException e) {
					// do nothing - phantom space or noninitialized graphics
				}
			}
		});
		frame.getContentPane().add(panel_graphic, BorderLayout.CENTER);
		
		// Add JPanel for the file text label
		panel_text = new JPanel();
		panel_text.setPreferredSize(new Dimension(0, 30));
		panel_text.setMinimumSize(new Dimension(0, 30));
		frame.getContentPane().add(panel_text, BorderLayout.SOUTH);
		
		// Add label to hold file description text
		label_text = new JLabel();
		label_text.setHorizontalAlignment(SwingConstants.LEFT);
		panel_text.add(label_text);
		
		printWelcomeMessage();
	}
	
	/**
	 * public Color_Abstract getColorType()
	 * returns the current abstract color type
	 * @return the color type
	 */
	public Color_Abstract getColorType() 
	{
		return colorType;
	}
	
	/**
	 * private void setColorType(Color_Abstract newColorType)
	 * sets a new color type and updates the graphics
	 * @param newColorType - the new color type to set
	 */
	private void setColorType(Color_Abstract newColorType) 
	{
		colorType = newColorType;
		graphicsChanged();
	}
	
	/**
	 * public Metric_Abstract getMetricType()
	 * returns the current abstract metric type
	 * @return the metric type
	 */
	public Metric_Abstract getMetricType() 
	{
		return metricType;
	}
	
	/**
	 * private void setMetricType(Metric_Abstract newMetricType)
	 * sets a new metricType before reinitializing the file struct, then updates graphics
	 * @param newMetricType - the new metric type to set
	 */
	private void setMetricType(Metric_Abstract newMetricType) 
	{
		metricType = newMetricType;
		gdm_model.initializeDirectory(gdm_model.getTreeDirectory().toFile(), this);
		graphicsChanged();
	}

	/**
	 * private void printWelcomeMessage()
	 * prints a welcome message
	 */
	private void printWelcomeMessage()
	{
		label_text.setText("Choose a Directory");
	}
	
	/**
	 * public File chooseDirectory()
	 * creates a JFileChooser so the user can select a directory
	 * @return the chosen file
	 */
	public File chooseDirectory()
	{
		File directory = null;
		int  chooserReturnValue;
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooserReturnValue = jFileChooser.showOpenDialog(null);
		if (chooserReturnValue == JFileChooser.APPROVE_OPTION)
		{
			directory = jFileChooser.getSelectedFile();
		}
		return directory;
	}
	
	/**
	 * private boolean modelStable()
	 * @return the result of the call to the model's isStable method
	 */
	private boolean modelStable() {
		return gdm_model.isStable();
	}
	
	/**
	 * public void error_notDirectory()
	 * prints a message if the chosen file was not a directory
	 */
	public void error_notDirectory()
	{
		repaint_timer.start();
		label_text.setText("Error: File chosen was not a directory");
	}
	
	/**
	 * public void error_noFileChosen()
	 * prints a message if no file was chosen
	 */
	public void error_noFileChosen()
	{
		repaint_timer.start();
		label_text.setText("Error: No file was chosen");
	}
	
	/**
	 * private void refreshDirectory()
	 * refreshes the current directory file struct
	 */
	private void refreshDirectory() 
	{
		repaint_timer.stop();
		gdm_model.refreshDirectory(this);
		graphicsChanged();
		repaint_timer.start();
	}
	
	/**
	 * private void changeDirectoryUp()
	 * stops the timer, has the model move up a directory, updates the graphics, then restarts the timer
	 */
	private void changeDirectoryUp() 
	{
		repaint_timer.stop();
		gdm_model.changeDirectoryUp(this);
		graphicsChanged();
		repaint_timer.start();
	}
	
	/**
	 * private void changeDirectoryDown(int x, int y)
	 * stops the timer, has the model move down a directory, updates the graphics, then restarts the timer
	 * @param x - the x coordinate of the mouse click
	 * @param y - the y coordinate of the mosue click
	 */
	private void changeDirectoryDown(int x, int y)
	{
		repaint_timer.stop();
		gdm_model.changeDirectoryDown(x,y,this);
		graphicsChanged();
		repaint_timer.start();
	}
	
	/**
	 * private void setFileDescription(int x, int y)
	 * sets the label to the file description retrieved from the model
	 * @param x - the x coordinate of the mouse
	 * @param y - the y coordinate of the mouse
	 */
	private void setFileDescription(int x, int y)
	{
		label_text.setText(gdm_model.getFileDescription(x,y, metricType));
	}
	
	/**
	 * public int getCanvasWidth()
	 * returns the width of the graphical panel
	 * @return the width of the graphical panel
	 */
	public int getCanvasWidth() 
	{
		return panel_graphic.getWidth()-1;
	}
	
	/**
	 * public int getCanvasHeight()
	 * returns the height of the graphical panel
	 * @return the height of the graphical panel
	 */
	public int getCanvasHeight() 
	{
		return panel_graphic.getHeight()-1;
	}
	
	/**
	 * private boolean viewDrawn()
	 * returns the value of viewDrawn
	 * @return viewDrawn
	 */
	private boolean viewDrawn() 
	{
		return viewDrawn;
	}
	
	/**
	 * public void graphicsChanged()
	 * draws a BufferedImage to the graphic panel, which is constructed by a call to the other graphicsChanged method
	 */
	public void graphicsChanged() 
	{
		viewDrawn = false;
		Graphics2D g2 = (Graphics2D) panel_graphic.getGraphics();
		BufferedImage bufferedImage = (BufferedImage) panel_graphic.createImage(getCanvasWidth(), getCanvasHeight());
		Graphics2D bufferedImageGraphics = bufferedImage.createGraphics();
		graphicsChanged(bufferedImageGraphics);
		g2.drawImage(bufferedImage, 0, 0, panel_graphic);
		viewDrawn = true;
	}
	
	/**
	 * private void graphicsChanged(Graphics2D g2)
	 * calls drawRectangle and drawOutlines to draw the treeMap
	 * @param g2 - the current Graphics2D object to write to
	 */
	private void graphicsChanged(Graphics2D g2) 
	{
		drawRectangle(g2, gdm_model.getTreeDirectory());
		g2.setColor(Color.WHITE);
		drawOutlines(g2, gdm_model.getTreeDirectory());
		panel_graphic.paintComponents(g2);
	}
	
	/**
	 * private void resetRectangles()
	 * resets the model's rectangles for drawing
	 */
	private void resetRectangles() 
	{
		gdm_model.initializeRectangleSizes(getCanvasWidth(), getCanvasHeight(), colorType);
	}
	
	/**
	 * private void drawRectangle(Graphics2D g2, Tree_Directory directory)
	 * recursively loops through the components of the directory, drawing each one
	 * @param g2 - the current Graphics2D object to write to
	 * @param directory - the current directory to draw
	 */
	private void drawRectangle(Graphics2D g2, Tree_Directory directory) 
	{
		if (directory.isActive()) {
			g2.setColor(Color.WHITE);
			g2.drawRect(directory.getPositionX(), directory.getPositionY(), directory.getSizeX(), directory.getSizeY());
		}
		
		Iterator<Tree_Component> iter = directory.getIterator();
		while(iter.hasNext()) {
			Tree_Component treeComponent = iter.next();
			if (treeComponent.isDirectory()) {
				drawRectangle(g2, (Tree_Directory) treeComponent);
			}
			else {
				try {
					g2.setColor(((Tree_File) treeComponent).getColor());
					g2.fillRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
					g2.setColor(Color.BLACK);
					g2.drawRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
				} catch (ClassCastException e) {
					// do nothing - bug with ghost files under Windows
				}
			}
		}
	}
	
	/**
	 * private void drawOutlines(Graphics2D g2, Tree_Component treeComponent)
	 * recursively loops through the components of the directory, drawing outlines for the ones that contain the mouse pointer
	 * @param g2 - the current Graphics2D object to write to
	 * @param treeComponent - the current component to draw
	 */
	private void drawOutlines(Graphics2D g2, Tree_Component treeComponent) 
	{
		g2.drawRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
		if (treeComponent.isDirectory()) {
			Iterator<Tree_Component> iter = ((Tree_Directory) treeComponent).getIterator();
			while(iter.hasNext()) {
				Tree_Component childComponent = iter.next();
				if (childComponent.isActive()) {
					drawOutlines(g2, childComponent);
					break;
				}
			}
		}
	}
}
