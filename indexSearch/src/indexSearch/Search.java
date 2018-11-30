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
	public Object search(String keywords) throws NumberFormatException, IOException {
		String[] keys = keywords.split(" ");// keyword respectively
		FileInputStream inputStream = new FileInputStream("index.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String tmp = "";
		while((tmp = bufferedReader.readLine()) != null) {
			String[] s = tmp.split(" ");
			int length = s.length;
			System.out.println(length);
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
		}
		System.out.println(index);
		HashSet<Integer> res = new HashSet<Integer>(index.get(keys[0]));
		for(String key: keys) {
			res.addAll(index.get(key));
			//res.retainAll(index.get(key));
		}
		if(res.size() == 0) {
			System.out.println("Not found");
            return 0;
		}
		return res;
//		System.out.println("Found in: ");
//        for(int num : res){
//            System.out.println("\t"+sources.get(num));
//        }
	}
	public void rank() {
		//term
		//docId
		//freq
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Search s =  new Search();
		Scanner in = new Scanner(System.in);
		String ss = in.nextLine();
		System.out.println(s.search(ss));
	}
}
