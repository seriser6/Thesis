import java.io.File;


public abstract class Tree_Component {
	protected String name;
	protected Tree_Directory parent;
	protected long metricValue;
	protected int positionX, positionY, sizeX, sizeY;
	protected boolean active;

	public Tree_Component(File file, Tree_Directory parent)
	{
		this.parent = parent;
		name = file.getName();
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

	abstract void setMetricValue(Metric_Abstract metricType);
	
	public String getName()
	{
		return name;
	}
	
	public String getParentPath()
	{
		return parent.getPath() + File.separator;
	}
	
	public String getPath()
	{
		return getParentPath() + getName();
	}
	
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	abstract boolean isDirectory();
	
	public boolean isActive() {
		return active;
	}
	
	abstract void activate();
	
	abstract void deactivate();
}
