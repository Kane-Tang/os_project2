package indexSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server{
	private final int portNum;
	public static LinkedHashSet<Integer> ans = new LinkedHashSet<Integer>();
	
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
								Server.ans.clear();
								//HashSet<Integer> ans = new HashSet<Integer>();
								ExecutorService threadPool = Executors.newFixedThreadPool(10);
								threadPool.submit(new Helper(1,inputLine));
								threadPool.submit(new Helper(2,inputLine));
								threadPool.submit(new Helper(3,inputLine));
								threadPool.submit(new Helper(4,inputLine));
								threadPool.submit(new Helper(5,inputLine));
								threadPool.submit(new Helper(6,inputLine));
								threadPool.submit(new Helper(7,inputLine));
								threadPool.submit(new Helper(8,inputLine));
								threadPool.submit(new Helper(9,inputLine));
								threadPool.submit(new Helper(10,inputLine));

								
								try {
									threadPool.awaitTermination(1, TimeUnit.SECONDS);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println(ans);
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
