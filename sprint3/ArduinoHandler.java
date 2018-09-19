package sprint3;
import java.io.DataInputStream;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Klassen h�ller kontakten mellan bilarna och server klassen.
 * @author Nazdar & Zoubida
 * 
 */
public class ArduinoHandler extends Thread {
	private byte message;
	private Socket arduinoClientSocket;
	private DataOutputStream dos1;
	private DataInputStream dis;
	private int id;
	private Controller cr;

	/**
	 * konstruktor som tilldelar arduinoClientSocket och id ett v�rdet 
	 * @param arduinoClientSocket socket mellan arduino och server klassen
	 * @param id p� inkopplade bilar
	 * @param controller object f�r klassen Controller
	 */
	public ArduinoHandler(Socket arduinoClientSocket, int id, Controller controller) {
		this.arduinoClientSocket = arduinoClientSocket;
		this.id = id;
		this.cr = controller;

	}

	/**
	 * run metoden som tar emot string fr�n arduino
	 * 
	 */
	public void run(){
		while(true){
			try{
				dis = new DataInputStream(arduinoClientSocket.getInputStream());
				dos1 = new DataOutputStream(arduinoClientSocket.getOutputStream());

				message = dis.readByte();
				System.out.println(id + ",skickar det fr�n arduino:" + message);

				if (message == 90){
					cr.countupPlayer(id);
				}
				if (message == 75){
					cr.countdownPlayer(id); 
				}
				if (message == 102){
					cr.winner();
					System.out.println("resultatet �r: "+ cr.winner());
				}


			}catch(Exception e2){
				e2.printStackTrace();
			}

		}

	}

	/**
	 * metoden skickar vidare string som kommer fr�n appen till bilarna
	 * @param message string som kommer fr�n appen via server
	 * @throws Exception
	 */

	public void sendMessage(String message)throws Exception{

		dos1.writeUTF(message);
		System.out.println("jag skickar det till arduino"+ id +":" + message);
		dos1.flush();
	}

}