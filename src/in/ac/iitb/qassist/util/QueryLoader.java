package in.ac.iitb.qassist.util;

import java.io.IOException;
import java.util.ResourceBundle;

public class QueryLoader {

	private static QueryLoader loader;
	private ResourceBundle props;

	private QueryLoader() throws IOException {
		props = ResourceBundle.getBundle("queries");
	}

	public static QueryLoader getInstance() throws IOException {
		if (null == loader)
			loader = new QueryLoader();
		return loader;
	}

	public String getOutlinksQuery() {
		return props.getString("query.outlinks");
	}
	
	public String getPagesQuery()	{
		return props.getString("query.pages");
	}

}
