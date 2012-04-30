import java.io.File;


public abstract class Tree_Component {
	protected String name;
	protected Tree_Directory parent;
	protected int dimension; // dimensions for the size thing
	protected long metricValue;
	protected int positionX, positionY, sizeX, sizeY;

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
		return parent.getPath();
	}
	
	public String getPath()
	{
		return getParentPath() + getName();
	}
	
	public abstract void makeDimension(int dimension);
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	abstract boolean isDirectory();
}
