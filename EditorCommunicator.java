import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Handles communication to/from the server for the editor
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author Chris Bailey-Kellogg; overall structure substantially revised Winter 2014
 * @author Travis Peters, Dartmouth CS 10, Winter 2015; remove EditorCommunicatorStandalone (use echo server for testing)
 * @author Aryan Dawer, Dartmouth CS 10, Fall 2022
 * @author Lindsey Drumm, Dartmouth CS 10, Fall 2022
 */
public class EditorCommunicator extends Thread {
	private PrintWriter out;		// to server
	private BufferedReader in;		// from server
	protected Editor editor;		// handling communication for

	/**
	 * Establishes connection and in/out pair
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		System.out.println("connecting to " + serverIP + "...");
		try {
			Socket sock = new Socket(serverIP, 4242);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("...connected");
		}
		catch (IOException e) {
			System.err.println("couldn't connect");
			System.exit(-1);
		}
	}

	/**
	 * Sends message to the server
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the server
	 */
	public void run() {
		try {
			// Handle messages
			// TODO: YOUR CODE HERE
			String inp;
			while((inp = in.readLine())!=null){
				MessageReader.readMessage(inp, editor.getSketch()); //interprets given message and acts on it
				editor.repaint(); // repainting to update after new changes
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("server hung up");
		}
	}	

	// Send editor requests to the server
	// TODO: YOUR CODE HERE

	/**
	 * requests SketchServerCommunicator to draw shape
	 * @param shape
	 */
	public synchronized void requestDraw(Shape shape){
		send("draw " + shape.toString());
	}

	/**
	 * requests SketchServerCommunicator to delete shape at ID
	 * @param ID
	 */
	public synchronized void requestDelete(int ID) {
		send("delete " + ID);
	}

	/**
	 * requests SketchServerCommunicator to move shape at ID by dx and dy
	 * @param ID
	 * @param dx
	 * @param dy
	 */
	public synchronized void requestMove(int ID, int dx, int dy) {
		send("move " + ID + " " + dx + " " + dy);
	}

	/**
	 * requests SketchServerCommunicator to recolor shape at ID
	 * @param ID
	 * @param color
	 */
	public synchronized void requestRecolor(int ID, Color color){
		send("recolor " + ID + " " + color.getRGB());
	}
	
}
