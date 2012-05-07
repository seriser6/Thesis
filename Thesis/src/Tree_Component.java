import java.io.File;

/**
 * Tree_Component
 * An abstract class representing a node in the file tree structure
 */
public abstract class Tree_Component 
{
	// The name of the file
	private String name;
	// A reference to the node's parent
	private Tree_Directory parent;
	// The value of the file's metric
	private long metricValue;
	// The values determining the size of the node's rectangle representation
	private int positionX, positionY, sizeX, sizeY;
	// A boolean representing whether or not a given node rectangle space contains the mouse
	private boolean active;
	// A boolean determining if this file is a directory
	private boolean directory;

	/**
	 * public Tree_Component(File file, Tree_Directory parent)
	 * The constructor for a Tree_Component
	 * @param file - the file to construct this node with
	 * @param parent - the parent of this node
	 */
	public Tree_Component(File file, Tree_Directory parent)
	{
		this.parent = parent;
		name = file.getName();
		directory = false;
	}

	/**
	 * protected void setMetricValue(long metricValue) '
	 * setter for metricValue
	 * @param metricValue - the new metricValue
	 */
	protected void setMetricValue(long metricValue) 
	{
		this.metricValue = metricValue;
	}

	/**
	 * protected void setActive(boolean active) 
	 * setter for active
	 * @param active - the new active
	 */
	protected void setActive(boolean active) 
	{
		this.active = active;
	}

	/**
	 * protected void setDirectory(boolean directory) 
	 * setter for directory
	 * @param directory - the new directory
	 */
	protected void setDirectory(boolean directory) 
	{
		this.directory = directory;
	}

	/**
	 * public long getMetricValue()
	 * getter for metricValue
	 * @return metricValue
	 */
	public long getMetricValue()
	{
		return metricValue;
	}
	
	/**
	 * public int getPositionX() 
	 * getter for positionX
	 * @return positionX
	 */
	public int getPositionX() 
	{
		return positionX;
	}

	/**
	 * public int getPositionY() 
	 * getter for positionY
	 * @return positionY
	 */
	public int getPositionY() 
	{
		return positionY;
	}

	/**
	 * public int getSizeX() 
	 * getter for sizeX
	 * @return sizeX
	 */
	public int getSizeX() 
	{
		return sizeX;
	}

	/**
	 * public int getSizeY() 
	 * getter for sizeY
	 * @return sizeY
	 */
	public int getSizeY() 
	{
		return sizeY;
	}

	/**
	 * public String getName()
	 * getter for name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * protected Tree_Directory getParent() 
	 * getter for parent, the parent of this node
	 * @return parent
	 */
	protected Tree_Directory getParent() 
	{
		return parent;
	}
	
	/**
	 * protected String getParentPath()
	 * returns the String representation of the parent of this file's path
	 * @return the parent's path
	 */
	protected String getParentPath()
	{
		return parent.getPath() + File.separator;
	}
	
	/**
	 * public String getPath()
	 * returns the String representation of the file's path
	 * @return the path
	 */
	public String getPath()
	{
		return getParentPath() + getName();
	}
	
	/**
	 * protected void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType)
	 * sets the values pertaining to the node's rectangular representation
	 * @param sizeX - the horizontal size of the rectangle
	 * @param sizeY - the vertical size of the rectangle
	 * @param posX - the x position of the rectangle
	 * @param posY - the y position of the rectangle
	 * @param horizontal - not used, intended for subclass
	 * @param colorType - not used, intended for subclass
	 */
	protected void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) 
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	/**
	 * public boolean isDirectory()
	 * returns the value of the boolean directory
	 * @return directory
	 */
	public boolean isDirectory() 
	{
		return directory;
	}
	
	/**
	 * public boolean isActive()
	 * returns the value of the boolean active
	 * @return active
	 */
	public boolean isActive() 
	{
		return active;
	}
	
	/**
	 * abstract void activate()
	 * method to be implemented by a subclass, which will be called when
	 * the mouse is in the node's rectangle space
	 */
	abstract void activate();
	
	/**
	 * abstract void activate()
	 * method to be implemented by a subclass, which will be called when
	 * the mouse leaves the node's rectangle space
	 */
	abstract void deactivate();
	
	/**
	 * public File toFile()
	 * returns a File object that pertains to this node
	 * @return a File object that pertains to this node
	 */
	public File toFile() 
	{
		return new File(getPath());
	}
}
