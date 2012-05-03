import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class GDM_View {

	// The GDM_Controller object
	private GDM_Controller gdm_controller;
	
	// The GDM_Model object
	private GDM_Model 	   gdm_model;
	
	//private GDM_View	   gdm_view;
	
	private Timer	  repaint_timer;
	
	private Metric_Abstract metricType;
	private Color_Abstract colorType;
	
	private boolean viewDrawn;
	
	// The GUI components
	private JFrame frame;
	private JPanel panel_text;
	private JButton btn_OpenDirectory;
	private JPanel panel_buttons;
	private JPanel panel_graphic;
	private JLabel label_text;
	private JComboBox<String> comboBox_metric;
	private JComboBox<String> comboBox_color;
	
	/**
	 * GDM_View()
	 * The constructor for the GDM_View object.
	 */
	public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	{
		//gdm_view = this;
		this.gdm_controller = gdm_controller;
		this.gdm_model = gdm_model;
		viewDrawn = false;
		metricType = new Metric_Size();
		colorType = new Color_FileType();
		createGUI();
		repaint_timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//repaint_timer.stop();
				if (modelStable() && viewDrawn()) {
					resetRectangles();
					graphicsChanged();
				}
			}
		});
		repaint_timer.start();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void createGUI()
	{
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(200, 100));
		/*
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				//repaint_timer.start();
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				//repaint_timer.start();
			}
		});
		*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1100,700));
		frame.setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		panel_buttons = new JPanel(true);
		frame.getContentPane().add(panel_buttons, BorderLayout.NORTH);
		panel_buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btn_OpenDirectory = new JButton("Open Directory");
		btn_OpenDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				repaint_timer.stop();
				gdm_controller.initializeDirectory();
			}
		});
		panel_buttons.add(btn_OpenDirectory);
		
		JLabel lblMetric = new JLabel("Size Metric");
		panel_buttons.add(lblMetric);
		
		//comboBox_metric = new JComboBox<String>(new String[] {"File Size", "Last Modified"});
		comboBox_metric = new JComboBox(new String[] {"File Size", "Last Modified"});
		comboBox_metric.setSelectedIndex(0);
		comboBox_metric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(comboBox_metric.getSelectedIndex()) {
				case 0:
					metricType = new Metric_Size();
					break;
				case 1:
					metricType = new Metric_LastModified();
					break;
				default:
					metricType = new Metric_Size();
					break;
				}
			}
		});
		panel_buttons.add(comboBox_metric);
		
		JLabel lblColor = new JLabel("Coloring");
		panel_buttons.add(lblColor);
		
		//comboBox_color = new JComboBox<String>(new String[] {"File Type"});
		comboBox_color = new JComboBox(new String[] {"File Type"});
		comboBox_color.setSelectedIndex(0);
		comboBox_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBox_color.getSelectedIndex()) {
				case 0:
					colorType = new Color_FileType();
					break;
				default:
					colorType = new Color_FileType();
					break;
				}
			}
		});
		panel_buttons.add(comboBox_color);
		
		panel_graphic = new JPanel();
		panel_graphic.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				setFileDescription(arg0.getX(),arg0.getY());
			}
		});
		frame.getContentPane().add(panel_graphic, BorderLayout.CENTER);
		
		panel_text = new JPanel();
		panel_text.setPreferredSize(new Dimension(0, 30));
		panel_text.setMinimumSize(new Dimension(0, 30));
		frame.getContentPane().add(panel_text, BorderLayout.SOUTH);
		
		label_text = new JLabel();
		label_text.setHorizontalAlignment(SwingConstants.LEFT);
		panel_text.add(label_text);
		
		printWelcomeMessage();
	}
	
	public Color_Abstract getColorType() {
		return colorType;
	}
	
	public Metric_Abstract getMetricType() {
		return metricType;
	}

	private void printWelcomeMessage()
	{
		label_text.setText("Choose a Directory");
	}
	
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
	
	public boolean modelStable() {
		return gdm_model.isStable();
	}
	
	public void error_notDirectory()
	{
		repaint_timer.start();
		label_text.setText("Error: File chosen was not a directory");
	}
	
	public void error_noFileChosen()
	{
		repaint_timer.start();
		label_text.setText("Error: No file was chosen");
	}
	
	public void setFileDescription(int x, int y) {
		label_text.setText(gdm_model.getFileDescription(x,y));
	}
	
	public int getCanvasWidth() {
		return panel_graphic.getWidth()-1;
	}
	
	public int getCanvasHeight() {
		return panel_graphic.getHeight()-1;
	}
	
	public boolean viewDrawn() {
		return viewDrawn;
	}
	
	public void graphicsChanged() {
		viewDrawn = false;
		Graphics2D g2 = (Graphics2D) panel_graphic.getGraphics();
		BufferedImage bufferedImage = (BufferedImage) panel_graphic.createImage(getCanvasWidth(), getCanvasHeight());
		Graphics2D bufferedImageGraphics = bufferedImage.createGraphics();
		graphicsChanged(bufferedImageGraphics);
		g2.drawImage(bufferedImage, 0, 0, panel_graphic);
		viewDrawn = true;
	}
	
	public void graphicsChanged(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		drawRectangle(g2, gdm_model.getTreeDirectory());
		g2.setColor(Color.WHITE);
		drawOutlines(g2, gdm_model.getTreeDirectory());
		panel_graphic.paintComponents(g2);
	}
	
	public void resetRectangles() {
		gdm_model.initializeRectangleSizes(getCanvasWidth(), getCanvasHeight(), colorType);
	}
	
	public void drawRectangle(Graphics2D g2, Tree_Directory directory) {
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
				g2.setColor(((Tree_File) treeComponent).getColor());
				g2.fillRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
				g2.setColor(Color.BLACK);
				g2.drawRect(treeComponent.getPositionX(), treeComponent.getPositionY(), treeComponent.getSizeX(), treeComponent.getSizeY());
			}
		}
	}
	
	public void drawOutlines(Graphics2D g2, Tree_Component treeComponent) {
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
