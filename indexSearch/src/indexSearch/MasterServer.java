package indexSearch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MasterServer {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		threadPool.submit(new Server(8888,1));
		threadPool.submit(new Server(8889,2));
		threadPool.submit(new Server(8890,3));
		threadPool.submit(new Server(8891,4));
		threadPool.submit(new Server(8892,5));
	}
}
