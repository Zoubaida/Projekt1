
package sprint3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * klassen Main är huvud klassen i server som innehåller alla serversocket med portnummer och innehåller alla tråder som tillhör klienterna
 * @author Nazdar & Zoubida
 *
 */
public class Main {
	private static ServerSocket androidServerSocket;
	private static ServerSocket arduinoServerSocket;

	protected static ArrayList<AndroidHandler> androidarr = new ArrayList<AndroidHandler>();
	protected static ArrayList<ArduinoHandler> arduinoarr = new ArrayList<ArduinoHandler>();


	/**
	 * main metoden som startar servern
	 * @param args
	 */
	public static void main (String args[]){	 
		Controller controller = new Controller();
		try {
			androidServerSocket = new ServerSocket(443);
			arduinoServerSocket = new ServerSocket(44344);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("Server started: ");	
		Thread andthread = new Thread(){

			/**
			 * run metoden som startar det första trådet för android
			 */
			public void run(){
				while(true){
					try{
						Socket androidClientSocket = androidServerSocket.accept();
						AndroidHandler androidhandler = new AndroidHandler(androidClientSocket, androidarr.size(), controller);
						Thread thread = new Thread(androidhandler);
						androidarr.add(androidhandler);
						System.out.println("this is idApp" + androidarr.size());
						thread.start();

					}catch(Exception e ){}
				}
			}
		};
		andthread.start();

		Thread ardthread = new Thread(){
			/**
			 * run metoden som startar det andra trådet för arduino
			 */
			public void run(){
				while(true){
					try{
						Socket arduinoClientSocket = arduinoServerSocket.accept();
						ArduinoHandler arduinohandler = new ArduinoHandler(arduinoClientSocket, arduinoarr.size(), controller);
						Thread thread2 = new Thread(arduinohandler);	
						arduinoarr.add(arduinohandler);
						System.out.println("this is idCar" + arduinoarr.size());
						thread2.start();
					}catch(Exception e2){}
				}
			}
		}; ardthread.start();
	}
}