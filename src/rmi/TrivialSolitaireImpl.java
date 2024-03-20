package rmi;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import common.*;

public class TrivialSolitaireImpl extends UnicastRemoteObject implements TrivialSolitaire {

	public static void main (String [] args) throws Exception {
		// launcher
		Registry registry  = LocateRegistry.createRegistry(1998);
		registry.bind("TrivialSolitaire", new TrivialSolitaireImpl());
		System.out.println("Trivial solitaire service running (registry in port 1998)...");
	}
	
	
	//---------------------------------------------------------------------
	
	private static List<Question> art, geo, science; // shouldn't modify these lists...
	
	/* COMPLETE 1a add other necessary attributes */

	private LinkedList<ClientInfo> clients = new LinkedList<ClientInfo>();
	
	// static initializer (initializes the lists)
	static {
		art = new LinkedList<Question>();
		geo = new LinkedList<Question>();
		science = new LinkedList<Question>();
		try {
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
		}
		catch (IOException ioex) {
			System.err.println("static initialization failed!!!");
			System.err.println(ioex);
			System.exit(0);
		}
	}
	
	public TrivialSolitaireImpl() throws RemoteException {
		/* COMPLETE if needed 1b: Constructor ... */
	}

	@Override
	public int Hello() throws RemoteException {
		return addClient();
	}

	@Override
	public Question next(int id, String type) throws RemoteException {
		return getClient(id).nextQuestion(type);	
	}

	@Override
	public void stop(int id) throws RemoteException {
		removeClient(id);
	}

	/* COMPLETE 2: implement interface and other helper methods */

	// Adds a client with an id and returns the id
	private int addClient() {
		int id = clients.size();
		this.clients.add(new ClientInfo(id, art, geo, science));
		return id;
	}

	private ClientInfo getClient(int id) {
		// Use use synchronized block to avoid concurrent modification
		ClientInfo response;
		synchronized(this.clients) {
			response = this.clients.stream().filter(c -> c.id == id).findFirst().get();
		}
		return response;
	}

	private void removeClient(int id) {
		// Use use synchronized block to avoid concurrent modification
		synchronized(this.clients) {
			ClientInfo client = this.getClient(id);
			this.clients.remove(client);
		}
	}
}


// consider using instances of this class to store relevant information regarding a particular client
// (like the questions that have not been been sent to it yet...)
class ClientInfo {
	/* COMPLETE */

	public final int id;
	private HashMap<String, LinkedList<Question>> questions;

	public ClientInfo(int id, List<Question> art, List<Question> geo, List<Question> science) {
		this.id = id;
		this.questions = new HashMap<String, LinkedList<Question>>();
		this.questions.put("ART", new LinkedList<Question>(art));
		this.questions.put("GEO", new LinkedList<Question>(geo));
		this.questions.put("SCIENCE", new LinkedList<Question>(science));
	}

	public Question nextQuestion(String type) {
		synchronized (this.questions) {
			LinkedList<Question> questions = this.questions.get(type);
			if (questions.size() > 0) {
				int index = (int) (Math.random() * questions.size());
				Question q = questions.get(index);
				questions.remove(index);
				return q;
			}
			return null;
		}
	} 
}
