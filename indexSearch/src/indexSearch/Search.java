package indexSearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Search {
	//Map<Integer,String> sources;
	HashMap<String, HashSet<Integer>> index;
	Search(){
		//sources = new HashMap<Integer,String>();
        index = new HashMap<String, HashSet<Integer>>();
    }
	public Object search(String keywords, int id) throws NumberFormatException, IOException {
		String[] keys = keywords.split(" ");// keyword respectively
		FileInputStream inputStream = new FileInputStream("index.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String tmp = "";
		int line = 1;
		while((tmp = bufferedReader.readLine()) != null) {
			String[] s = tmp.split(" ");
			int length = s.length;
			if(line > (id-1)*10 && line < id*10) {
				HashSet<Integer> hs = new HashSet<Integer>();
				for(int i=1;i<length;i+=2) {
					hs.add(Integer.parseInt(s[i]));
				}	
				if(index.containsKey(s[0])) {
					HashSet<Integer> t = new HashSet<Integer>();
					t = index.get(s[0]);
					hs.addAll(t);
				}
				index.put(s[0], hs);
				System.out.println(index);
			}
			line += 1;
		}
		int l = keys.length;
		if(index.isEmpty()) {
			return "not found";
		}
		for(String key: keys) {
			if(!index.containsKey(key)) {
				l -= 1;
			}
		}
		if(l == 0) {
			return "not found";
		}
		HashSet<Integer> res = new HashSet<Integer>(index.get(keys[0]));
		for(String key: keys) {
			res.addAll(index.get(key));
			//res.retainAll(index.get(key));
		}
		if(res.size() == 0) {
			System.out.println("Not found");
            return res;
		}
		return res;
	}
	public void rank() {
		//term
		//docId
		//freq
	}
	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		Search s =  new Search();
//		Scanner in = new Scanner(System.in);
//		String ss = in.nextLine();
//		System.out.println(s.search(ss));
//	}
}
