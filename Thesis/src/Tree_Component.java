import java.io.File;


public abstract class Tree_Component {
	private String name;
	private Tree_Directory parent;
	private long metricValue;
	private int positionX, positionY, sizeX, sizeY;
	private boolean active;
	private boolean directory;

	public Tree_Component(File file, Tree_Directory parent)
	{
		this.parent = parent;
		name = file.getName();
		directory = false;
	}

	protected void setMetricValue(long metricValue) {
		this.metricValue = metricValue;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	protected void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public long getMetricValue()
	{
		return metricValue;
	}
	
	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public String getName()
	{
		return name;
	}
	
	protected Tree_Directory getParent() {
		return parent;
	}
	
	protected String getParentPath()
	{
		return parent.getPath() + File.separator;
	}
	
	public String getPath()
	{
		return getParentPath() + getName();
	}
	
	protected void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	public boolean isDirectory() {
		return directory;
	}
	
	public boolean isActive() {
		return active;
	}
	
	abstract void activate();
	
	abstract void deactivate();
	
	public File toFile() {
		return new File(getPath());
	}
}
