import java.awt.Color;

/**
 * Color_Abstract
 * An interface used by the view, representing a color algorithm
 */
public interface Color_Abstract 
{

	/**
	 * public Color getColor(Tree_File file)
	 * returns the color that corresponds to the given file node
	 * @param file - the file to get a color for
	 * @return a Color object
	 */
	public Color getColor(Tree_File file);
}
