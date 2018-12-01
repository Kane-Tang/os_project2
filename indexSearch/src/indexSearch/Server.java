package indexSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
	private final int portNum;
	
	Server(int pn){
		this.portNum = pn;
	}
	
	public void start() {
		try 
		{
			ServerSocket serverSocket = new ServerSocket(this.portNum);
			Executor threadPool = Executors.newFixedThreadPool(100);
			while(true) {
				Socket clientSocket = serverSocket.accept();
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Socket conn = clientSocket;
							PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
							BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
							String inputLine;
							while ((inputLine = in.readLine()) != null) {
								Object res;
								HashSet<Integer> ans = new HashSet<Integer>();
								Helper h1 = new Helper(1,inputLine);
								res = h1.start();
								System.out.println(res);
								if(res.toString()!="not found") {
									ans = (HashSet)res;
								}
								Helper h2 = new Helper(2,inputLine);
								res = h2.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h3 = new Helper(3,inputLine);
								res = h3.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h4 = new Helper(4,inputLine);
								res = h4.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h5 = new Helper(5,inputLine);
								res = h5.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h6 = new Helper(6,inputLine);
								res = h6.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h7 = new Helper(7,inputLine);
								res = h7.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h8 = new Helper(8,inputLine);
								res = h8.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h9 = new Helper(9,inputLine);
								res = h9.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								Helper h10 = new Helper(10,inputLine);
								res = h10.start();
								if(res.toString()!="not found") {
									ans.addAll((HashSet)res);
								}
								//Search s = new Search();
								out.println(ans);
							}
						}catch(IOException e) {
							System.out.println(
									"Exception caught when trying to listen on port " + portNum + " or listening for a connection");
							System.out.println(e.getMessage());
						}
						
					}
				});
			}	
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + this.portNum + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNum = Integer.parseInt(args[0]);
		Server s = new Server(portNum);
		s.start();
	}
}
