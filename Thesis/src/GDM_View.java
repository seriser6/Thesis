import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	
	private GDM_View	   gdm_view;
	
	private Timer	  repaint_timer;
	
	// The GUI components
	private JFrame frame;
	private JPanel panel_text;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JButton btn_OpenDirectory;
	private JPanel panel_buttons;
	private JPanel panel_graphic;
	private JLabel label_text;
	
	private Graphics graphics;
	
	/**
	 * GDM_View()
	 * The constructor for the GDM_View object.
	 */
	public GDM_View(GDM_Controller gdm_controller, GDM_Model gdm_model)
	{
		gdm_view = this;
		this.gdm_controller = gdm_controller;
		this.gdm_model = gdm_model;
		repaint_timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//repaint_timer.stop();
				if (modelStable()) {
					resetRectangles();
					graphicsChanged();
				}
			}
		});
		repaint_timer.start();
		createGUI();
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
		//frame.setPreferredSize(new Dimension(1900, 1000));
		frame.setSize(new Dimension(1100,700));
		frame.setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		
		btn_OpenDirectory = new JButton("Open Directory");
		btn_OpenDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gdm_controller.initializeDirectory();
			}
		});
		panel_buttons.add(btn_OpenDirectory);
		
		btnNewButton_1 = new JButton("New button");
		panel_buttons.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("New button");
		panel_buttons.add(btnNewButton_2);
		
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
		
		//frame.pack();
	}

	private void printWelcomeMessage()
	{
		label_text.setText("Choose a Directory");
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
	
	public boolean modelStable() {
		return gdm_model.isStable();
	}
	
	public void error_notDirectory()
	{
		label_text.setText("Error: File chosen was not a directory");
	}
	
	public void error_noFileChosen()
	{
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
	
	public void graphicsChanged() {
		graphicsChanged(panel_graphic.getGraphics());
	}
	
	public void graphicsChanged(Graphics g) {
		panel_graphic.paint(g);
		drawRectangle(g, gdm_model.getTreeDirectory());
		backupGraphics();
	}
	
	public void backupGraphics() {
		graphics = panel_graphic.getGraphics().create();
	}
	
	public void restoreGraphics() {
		panel_graphic.paint(graphics);
	}
	
	public void resetRectangles() {
		gdm_model.initializeRectangleSizes(gdm_view);
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
