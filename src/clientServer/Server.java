package clientServer;

import java.io.*;
import java.net.*;
import java.util.*;

import common.Question;

public class Server  extends Thread {

	// LAUNCHER: server(s) spawner
	public static void main(String[] args) throws IOException {
		Socket connection;
		art = new LinkedList<Question>();
		geo = new LinkedList<Question>();
		science = new LinkedList<Question>();
		List<Question> questions = Question.fromFile(new File("Questions.txt"));
		for (Question question : questions) {
			switch (question.getType()) {
			case "GEO":
				geo.add(question);
				break;
			case "ART":
				art.add(question);
				break;
			case "SCIENCE":
				science.add(question);
				break;
			}
		}

		/* COMPLETE 1a: create ServerSocket and get ready to spawn new server instances 
		 * to service incoming connections (on demand approach) */
		ServerSocket serverSocket = new ServerSocket(4445); // Create a server socket on port 4445

		while (true) { // Loop forever, accepting client connections
			Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
			synchronized (clientSocket){
				Server server = new Server(clientSocket); // Create a new Server to handle the client
				server.start(); // Start the server thread
			}
		}

		
	}
	// LAUNCHER ENDS HERE
	
	private static List<Question> art, geo, science; // BEWARE! Static. 
	// instances shouldn't modify these lists

	private Socket connection;
	private BufferedReader inputChannel;
	private PrintWriter outputChannel;

	/* COMPLETE 1b: declare other necessary attributes here */
	
	
	
	public Server(Socket connection) throws IOException {
		this.connection = connection;
		this.inputChannel = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
		this.outputChannel = new PrintWriter(this.connection.getOutputStream(), true);
		/* COMPLETE 1bb: (optional) initialize other attributes */
	}

	public void run() {
		try {
			innerRun();
		} catch (IOException ioex) {
			ioex.printStackTrace(System.err);
		}
	}

	public void innerRun() throws IOException {
		/* COMPLETE 2 
		 * Here service one client */
		Request request;
		while (true){
			request = receiveRequest();
			if (request.type.equals("HELLO")) {
				sendReply("HELLO test server");
			}
			else if (request.type.equals("NEXT")) {
				switch (request.info) {
				case "GEO":
					if (geo.isEmpty()) {
						sendReply("NO MORE GEO questions");
					}
					else {
						sendReply(geo.remove(0).toString());
					}
					break;
				case "ART":
					if (art.isEmpty()) {
						sendReply("NO MORE ART questions");
					}
					else {
						sendReply(art.remove(0).toString());
					}
					break;
				case "SCIENCE":
					if (science.isEmpty()) {
						sendReply("NO MORE SCIENCE questions");
					}
					else {
						sendReply(science.remove(0).toString());
					}
					break;
				}
			}
			else if (request.type.equals("STOP")) {
				sendReply("GOODBYE");
				disconnect();
				break;
			}
		}
		
	}

	/* COMPLETE 3 (optional)
	 * Write here private methods for several purposes like
	 * getting a new question for the client, keeping track of the questions
	 * already sent to the client...
	 */
	

	private Request receiveRequest() throws IOException {
		String contents = inputChannel.readLine();
		Request request = new Request();
		int b = contents.indexOf(" "); // position of the first blank
		if (b<0) {
			request.type = contents;
			request.info ="";
		}
		else {
			request.type = contents.substring(0, b); // type of requested question goes from the beginning till the first blank
			request.info = contents.substring(b + 1).trim(); // information is everything following the first blank
		}
		return request;
	}

	private void sendReply(String reply) throws IOException {
		this.outputChannel.println(reply);
	}

	private void disconnect() throws IOException {
		this.connection.close();
		this.inputChannel.close();
		this.outputChannel.close();
	}

	/* PRIVATE Server-Side only class to represent requests */
	private class Request {
		public String type;  // type of request  (HELLO, NEXT, STOP)
		public String info;  // additional information (GEO, SCIENCE, ART)
	}

}
