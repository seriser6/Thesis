import java.awt.Color;
import java.io.File;


public class Color_LastModified implements Color_Abstract {

	static final long MILLIS_IN_YEAR = 31556926000L;
	static final long HALF_MILLIS_IN_YEAR = 15778463000L;
	private long currentTime;
	
	public Color_LastModified() {
		currentTime = System.currentTimeMillis();
	}
	
	@Override
	public Color getColor(Tree_File treeFile) {
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
