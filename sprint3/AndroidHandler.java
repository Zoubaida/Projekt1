package sprint3;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Klassen håller kontakten mellan applikationerna och server klassen.
 * @author Nazdar & Zoubida
 * 
 */
public class AndroidHandler extends Thread {
	private Socket androidClientSocket;
	private String message;
	private int id;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Controller cr;

	/**
	 * konstruktor som tilldelar androidClientSocket och id ett värdet 
	 * @param androidClientSocket socket mellan app och server
	 * @param id på inkopplade app
	 * @param controller object för klassen Controller
	 */
	public AndroidHandler(Socket androidClientSocket, int id, Controller controller){
		this.androidClientSocket = androidClientSocket;
		this.id = id;
		this.cr = controller;

	}

	/**
	 * run metoden som tar emot string från appen
	 * 
	 */
	public void run() {   
		while(true){
			try {
				dis = new DataInputStream(androidClientSocket.getInputStream());
				dos = new DataOutputStream(androidClientSocket.getOutputStream());
				while((message = dis.readUTF()) != null) {
					System.out.println("jag fick det från android" + id +":"+ message); 
					Main.arduinoarr.get(id).sendMessage(message);

					if (message.equals("11")){
						Main.androidarr.get(id).sendMessage(cr.winner());
					}
				} 
			} catch( Exception e) {}
		}
	}

	/**
	 * metoden skickar resultatet till appen
	 * @param result stringen som kallar vinnaren från Controller klass
	 * @throws Exception
	 */
	public void sendMessage(String result)throws Exception{
		result = cr.winner();
		dos.writeUTF(result);
		System.out.println("jag skickar det till android: " + result);
		dos.flush();

	}
}

