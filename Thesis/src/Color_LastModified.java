import java.awt.Color;
import java.io.File;

/**
 * Color_LastModified
 * implements Color_Abstract in order to be used as a coloring algorithm
 * operates so that newest files are bright green, oldest files are bright red.
 * files 6 months old are black. As they get newer they turn brighter green, and
 * as they get older they turn brighter red.
 */
public class Color_LastModified implements Color_Abstract 
{

	// constants for use by the formula
	static final long MILLIS_IN_YEAR = 31556926000L;
	static final long HALF_MILLIS_IN_YEAR = 15778463000L;
	
	// the current system time, for reference
	private long currentTime;
	
	/**
	 * public Color_LastModified()
	 * constructor, which is only used to store a persistent current system time
	 */
	public Color_LastModified() 
	{
		currentTime = System.currentTimeMillis();
	}
	
	/**
	 * public Color getColor(Tree_File file)
	 * returns the color that corresponds to the given file node
	 * @param file - the file to get a color for
	 * @return a Color object
	 */
	@Override
	public Color getColor(Tree_File treeFile) 
	{
		String filepath = treeFile.getPath();
		File file = new File(filepath);
		long fileage = currentTime - file.lastModified();
		if (fileage < 0)
			return new Color(0.0f,1.0f,0.0f);
		if (fileage > MILLIS_IN_YEAR)
			return new Color(1.0f,0.0f,0.0f);
		if (fileage > HALF_MILLIS_IN_YEAR)
			return new Color((float) ((double) (fileage-HALF_MILLIS_IN_YEAR)/(double)HALF_MILLIS_IN_YEAR),0.0f,0.0f);
		else if (fileage <= HALF_MILLIS_IN_YEAR)
			return new Color(0.0f,(float) ((double)(HALF_MILLIS_IN_YEAR-fileage)/(double)HALF_MILLIS_IN_YEAR),0.0f);
		else return new Color(1.0f,0.0f,0.0f);
	}
}
