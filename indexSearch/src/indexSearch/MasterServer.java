package indexSearch;

public class MasterServer {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);
	}
}
