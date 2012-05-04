import java.awt.Color;
import java.io.File;


public class Tree_File extends Tree_Component {
	
	protected Color color;
	
	public Tree_File(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		setMetricValue(metricType, file);
	}
	
	public Tree_File(Tree_Directory directory) {
		super(new File(directory.getPath()),directory.getParent());
		metricValue = directory.getMetricValue();
	}

	public void setMetricValue(Metric_Abstract metricType, File file)
	{
		metricValue = metricType.getMetricValue(file);
	}

	@Override
	void setMetricValue(Metric_Abstract metricType) {
		setMetricValue(metricType, new File(getPath()));
	}
	
	public Color getColor() {
		return color;
	}
	
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) {
		super.makeRectangle(sizeX, sizeY, posX, posY, horizontal, colorType);
		color = colorType.getColor(this);
	}
	
	public void activate() {
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
}
