package indexSearch;

import java.io.IOException;

public class Helper {
	private final int id;
	private final String keywords;
	Helper(int id, String keywords){
		this.id  = id;
		this.keywords = keywords;
	}
	public Object start() throws NumberFormatException, IOException {
		Search s = new Search();
		return s.search(this.keywords, this.id);
	}
}
