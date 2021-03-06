import java.awt.Color;

/**
 * Color_FileType
 * implements Color_Abstract in order to be used as a coloring algorithm
 * operates so that videos are red, images are yellow, audio is pink, documents are blue,
 * scripts are green, code is orange, databases are cyan, compressed files are dark gray,
 * and everything else is light gray.
 */
public class Color_FileType implements Color_Abstract 
{	
	
	// String array of image extensions
	private String[] extImage = new String[] {
			"act",
			"pal",
			"ase",
			"art",
			"bmp",
			"blp",
			"cit",
			"cpt",
			"cut",
			"dds",
			"dib",
			"djvu",
			"egt",
			"exif",
			"gif",
			"gpl",
			"grf",
			"icns",
			"ico",
			"iff",
			"ilbm",
			"lbm",
			"jng",
			"jpeg",
			"jfif",
			"jpg",
			"jp2",
			"jps",
			"lbm",
			"max",
			"miff",
			"mng",
			"msp",
			"msp",
			"nitf",
			"ota",
			"pbm",
			"pc1",
			"pc2",
			"pc3",
			"pcf",
			"pcx",
			"pdn",
			"pgm",
			"pl1",
			"pl2",
			"pl3",
			"pict",
			"pct",
			"png",
			"pnm",
			"pns",
			"ppm",
			"psb",
			"psd",
			"pdd",
			"psp",
			"px",
			"pxr",
			"qfx",
			"raw",
			"rle",
			"sct",
			"sgi",
			"rgb",
			"int",
			"bw",
			"tga",
			"targa",
			"icb",
			"vda",
			"vst",
			"pix",
			"tif",
			"tiff",
			"vtf",
			"xbm",
			"xcf",
			"xpm",
			"amf",
			"awg",
			"3dv",
			"ai",
			"eps",
			"cgm",
			"cdr",
			"cmx",
			"dxf",
			"e2d",
			"egt",
			"odg",
			"svg",
			"stl",
			"wrl",
			"x3d",
			"byu",
			"sxd",
			"v2d",
			"wmf",
			"emf",
			"art",
			"xar",
			"3dmf",
			"3dm",
			"3ds",
			"ac",
			"amf",
			"an8",
			"aoi",
			"b3d",
			"blend",
			"block",
			"c4d",
			"cal3d",
			"ccp4",
			"cfl",
			"cob",
			"core3d",
			"ctm",
			"dae",
			"dff",
			"dpm",
			"dts",
			"egg",
			"fact",
			"fac",
			"fbx",
			"g",
			"glm",
			"jas",
			"lwo",
			"lws",
			"lxo",
			"ma",
			"max",
			"mb",
			"md2",
			"md3",
			"mdx",
			"m",
			"mesh",
			"mm3d",
			"mpo",
			"mrc",
			"nif",
			"obj",
			"off",
			"prc",
			"pov",
			"rwx",
			"sia",
			"sib",
			"skp",
			"sldasm",
			"sldprt",
			"smd",
			"u3d",
			"wrl",
			"vue",
			"wings",
			"x",
			"x3d",
			"z3d"
	};
	// String array of video extensions
	private String[] extVideo = new String[] {
			"aaf",
			"3gp",
			"gif",
			"asf",
			"avchd",
			"avi",
			"cam",
			"dat",
			"dsh",
			"flv",
			"m1v",
			"m2v",
			"fla",
			"flr",
			"sol",
			"m4v",
			"mkv",
			"wrap",
			"mng",
			"mov",
			"mpeg",
			"mpg",
			"mpe",
			"mxf",
			"roq",
			"nsv",
			"ogg",
			"rm",
			"svi",
			"smi",
			"swf",
			"wmv",
			"fcp",
			"mswmm",
			"ppj",
			"imovieproj",
			"veg",
			"veg-bak",
			"suf",
			"wlmp"
	};
	// String array of audio extensions
	private String[] extAudio = new String[] {
			"aiff",
			"au",
			"bwf",
			"cdda",
			"iff-8svx",
			"iff-16sv",
			"raw",
			"wav",
			"flac",
			"la",
			"pac",
			"m4a",
			"ape",
			"rka",
			"shn",
			"tta",
			"wv",
			"wma",
			"brstm",
			"ast",
			"amr",
			"mp2",
			"mp3",
			"speex",
			"ogg",
			"gsm",
			"aac",
			"m4a",
			"mp4",
			"m4p",
			"mpc",
			"vqf",
			"ra",
			"rm",
			"ots",
			"swa",
			"vox",
			"voc",
			"dwd",
			"smp",
			"aup",
			"band",
			"cust",
			"mid",
			"mus",
			"sib",
			"ly",
			"gym",
			"vgm",
			"psf",
			"nsf",
			"mod",
			"ptb",
			"s3m",
			"xm",
			"it",
			"mt2",
			"mng",
			"bgm",
			"psf",
			"minipsf",
			"psflib",
			"2sf",
			"dsf",
			"gsf",
			"psf2",
			"qsf",
			"ssf",
			"usf",
			"rmj",
			"spc",
			"niff",
			"txm",
			"ym",
			"jam",
			"asf",
			"mp1",
			"mscz",
			"asx",
			"m3u",
			"pls",
			"ram",
			"xpl",
			"xspf",
			"zpl",
			"als",
			"aup",
			"band",
			"cel",
			"cpr",
			"cwp",
			"drm",
			"mmr",
			"npr",
			"omfi",
			"ses",
			"sfl",
			"sng",
			"stf",
			"snd",
			"syn"
	};
	// String array of document extensions
	private String[] extDocument = new String[] {
			"602",
			"abw",
			"acl",
			"afp",
			"ans",
			"asc",
			"aww",
			"ccf",
			"csv",
			"cwk",
			"doc",
			"docx",
			"dot",
			"dotx",
			"egt",
			"fdx",
			"ftm",
			"ftx",
			"html",
			"hwp",
			"hwpml",
			"lwp",
			"mbp",
			"mcw",
			"mobi",
			"nb",
			"nbp",
			"odm",
			"odt",
			"ott",
			"omm",
			"pages",
			"pap",
			"pdax",
			"pdf",
			"rtf",
			"quox",
			"rpt",
			"sdw",
			"stw",
			"sxw",
			"tex",
			"info",
			"troff",
			"uof",
			"uoml",
			"via",
			"wpd",
			"wps",
			"wpt",
			"wrd",
			"wrf",
			"wri",
			"xhtml",
			"xml",
			"xps"
	};
	// String array of script extensions
	private String[] extScript = new String[] {
			"ahk",
			"applescript",
			"as",
			"au3",
			"bat",
			"bas",
			"cmd",
			"coffee",
			"egg",
			"egt",
			"erb",
			"hta",
			"ibi",
			"ici",
			"itcl",
			"js",
			"jsfl",
			"lua",
			"m",
			"mrc",
			"ncf",
			"nut",
			"php",
			"php3",
			"php4",
			"php5",
			"phps",
			"pl",
			"pm",
			"ps1",
			"ps1xml",
			"psc1",
			"psd1",
			"psm1",
			"py",
			"pyc",
			"pyo",
			"r",
			"rb",
			"rdp",
			"scpt",
			"scptd",
			"sdl",
			"sh",
			"tcl",
			"vbs",
			"xpl",
			"ebuild"
	};
	// String array of code extensions
	private String[] extCode = new String[] {
			"ada",
			"adb",
			"ads",
			"asm",
			"s",
			"bas",
			"bb",
			"bmx",
			"c",
			"clj",
			"cls",
			"cob",
			"cbl",
			"cpp",
			"cc",
			"cxx",
			"cs",
			"csproj",
			"d",
			"dba",
			"dbpro",
			"e",
			"efs",
			"egt",
			"el",
			"for",
			"ftn",
			"f",
			"f77",
			"f90",
			"frm",
			"frx",
			"ged",
			"gm6",
			"gmd",
			"gmk",
			"gml",
			"go",
			"h",
			"hpp",
			"hxx",
			"hs",
			"inc",
			"java",
			"l",
			"lisp",
			"m",
			"m4",
			"ml",
			"n",
			"nb",
			"pas",
			"pp",
			"p",
			"php",
			"php3",
			"php4",
			"php5",
			"phps",
			"phtml",
			"piv",
			"pl",
			"pm",
			"prg",
			"py",
			"rb",
			"resx",
			"rc",
			"rc2",
			"rkt",
			"rktl",
			"sci",
			"sce",
			"scm",
			"skb",
			"skc",
			"skd",
			"skf",
			"skg",
			"ski",
			"skk",
			"skm",
			"sko",
			"skp",
			"skq",
			"sks",
			"skt",
			"skz",
			"sln",
			"stk",
			"vap",
			"vb",
			"vbp",
			"vip",
			"vbg",
			"vbproj",
			"vcproj",
			"vdproj",
			"xpl",
			"xq",
			"xsl",
			"y"
	};
	// String array of database extensions
	private String[] extDatabase = new String[] {
			"accdb",
			"adt",
			"apr",
			"box",
			"chml",
			"daf",
			"dat",
			"db",
			"dbf",
			"egt",
			"ess",
			"eap",
			"fdb",
			"fp",
			"fp3",
			"fp5",
			"fp7",
			"frm",
			"gdb",
			"kexi",
			"kexic",
			"ldb",
			"mdb",
			"adp",
			"mde",
			"mdf",
			"myd",
			"myi",
			"ncf",
			"nsf",
			"ntf",
			"nv2",
			"odb",
			"ora",
			"pdb",
			"pdi",
			"pdx",
			"prc",
			"sql",
			"rec",
			"rel",
			"rin",
			"sdb",
			"udl",
			"wdb",
			"wmdb"
	};
	// String array of compressed extensions
	private String[] extCompressed = new String[] {
			"a",
			"ar",
			"cpio",
			"shar",
			"lbr",
			"iso",
			"mar",
			"tar",
			"bz2",
			"f",
			"gz",
			"lz",
			"lzma",
			"lzo",
			"rz",
			"sfark",
			"?q?",
			"?z?",
			"xz",
			"z",
			"infl",
			"??_",
			"7z",
			"s7z",
			"ace",
			"afa",
			"alz",
			"apk",
			"arc",
			"arj",
			"ba",
			"bh",
			"cab",
			"cfs",
			"cpt",
			"dar",
			"dd",
			"dgc",
			"dmg",
			"gca",
			"ha",
			"hki",
			"ice",
			"j",
			"kgb",
			"lzh",
			"lha",
			"lzx",
			"pak",
			"partimg",
			"paq6",
			"paq7",
			"paq8",
			"pea",
			"pim",
			"pit",
			"qda",
			"rar",
			"rk",
			"sda",
			"sea",
			"sen",
			"sfx",
			"sit",
			"sitx",
			"gz",
			"tgz",
			"bz2",
			"tbz2",
			"lzma",
			"tlz",
			"uc",
			"uc0",
			"uc2",
			"ucn",
			"ur2",
			"ue2",
			"uca",
			"uha",
			"wim",
			"xar",
			"xp3",
			"yz1",
			"zip",
			"zipx",
			"zoo",
			"zz",
			"ecc",
			"par",
			"par2"
	};
	
	/**
	 * public Color getColor(Tree_File file)
	 * returns the color that corresponds to the given file node
	 * @param file - the file to get a color for
	 * @return a Color object
	 */
	public Color getColor(Tree_File file) 
	{
		String filename = file.getName();
		
		int lastIndex = filename.lastIndexOf('.');
		
		if (lastIndex == -1) return Color.LIGHT_GRAY; // no period in filename
		
		String fileExt = filename.substring(lastIndex+1).toLowerCase();
		
		if (isImage(fileExt)) return Color.YELLOW;
		if (isVideo(fileExt)) return Color.RED;
		if (isAudio(fileExt)) return Color.PINK;
		if (isDocument(fileExt)) return Color.BLUE;
		if (isScript(fileExt)) return Color.GREEN;
		if (isCode(fileExt)) return Color.ORANGE;
		if (isDatabase(fileExt)) return Color.CYAN;
		if (isCompressed(fileExt)) return Color.DARK_GRAY;
		return Color.LIGHT_GRAY;
	}

	/**
	 * private boolean isCode(String fileExt)
	 * check to see if the given extension matches a code file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isCode(String fileExt) 
	{
		return stringMatch(fileExt, extCode);
	}

	/**
	 * private boolean isCompressed(String fileExt)
	 * check to see if the given extension matches a compressed file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isCompressed(String fileExt) 
	{
		return stringMatch(fileExt, extCompressed);
	}

	/**
	 * private boolean isDatabase(String fileExt)
	 * check to see if the given extension matches a database file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isDatabase(String fileExt) 
	{
		return stringMatch(fileExt, extDatabase);
	}

	/**
	 * private boolean isScript(String fileExt)
	 * check to see if the given extension matches a script file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isScript(String fileExt) 
	{
		return stringMatch(fileExt, extScript);
	}

	/**
	 * private boolean isDocument(String fileExt)
	 * check to see if the given extension matches a document file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isDocument(String fileExt)
	{
		return stringMatch(fileExt, extDocument);
	}

	/**
	 * private boolean isAudio(String fileExt)
	 * check to see if the given extension matches an audio file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isAudio(String fileExt) 
	{
		return stringMatch(fileExt, extAudio);
	}

	/**
	 * private boolean isVideo(String fileExt)
	 * check to see if the given extension matches a video file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isVideo(String fileExt) 
	{
		return stringMatch(fileExt, extVideo);
	}

	/**
	 * private boolean isImage(String fileExt)
	 * check to see if the given extension matches an image file
	 * @param fileExt - the extension of the file to colorize
	 * @return the outcome of the stringMatch method call
	 */
	private boolean isImage(String fileExt) 
	{
		return stringMatch(fileExt, extImage);
	}
	
	/**
	 * private boolean stringMatch(String fileExt, String[] extList)
	 * checks the list of strings for a match with the file extension
	 * @param fileExt - the file extension
	 * @param extList - the list to check for matches
	 * @return a boolean representing if the extension matches or not
	 */
	private boolean stringMatch(String fileExt, String[] extList) 
	{
		for (int i = 0; i < extList.length; i++) {
			if (fileExt.equals(extList[i])) {
				return true;
			}
		}
		return false;
	}
}
