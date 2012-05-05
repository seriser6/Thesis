import java.awt.Color;
import java.io.File;


public class Tree_File extends Tree_Component {
	
	private Color color;
	
	public Tree_File(File file, Tree_Directory parent, Metric_Abstract metricType)
	{
		super(file, parent);
		
		setMetricValue(metricType, file);
	}
	
	public Tree_File(Tree_Directory directory) {
		super(new File(directory.getPath()),directory.getParent());
		setMetricValue(directory.getMetricValue());
	}

	private void setMetricValue(Metric_Abstract metricType, File file)
	{
		setMetricValue(metricType.getMetricValue(file));
	}

	public Color getColor() {
		return color;
	}
	
	public void makeRectangle(int sizeX, int sizeY, int posX, int posY, boolean horizontal, Color_Abstract colorType) {
		super.makeRectangle(sizeX, sizeY, posX, posY, horizontal, colorType);
		color = colorType.getColor(this);
	}
	
	public void activate() {
		setActive(true);
	}
	
	public void deactivate() {
		setActive(false);
	}
}
