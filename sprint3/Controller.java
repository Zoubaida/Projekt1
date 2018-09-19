package sprint3;

/**
 * Klassen inneh�ller antal po�ng, v�gsrtyckan och vinnaren 
 * @author Nazdar & Ansam & Namra
 * 
 */
public class Controller {

	private int playerAScore = 0;


	private int playerBScore = 0;



	private int playerADistance = 0;

	private int playerBDistance = 0;

	/**
	 * metoden inneh�ller antal po�ng f�r player
	 * @param id f�r bilen som krascha
	 * 
	 */
	public void countdownPlayer(int id) {
		if (id==0) {
			playerAScore = playerAScore - 10;
			System.out.println("playerA har:" + playerAScore);

		}else if (id==1){

			playerBScore = playerBScore - 10;
			System.out.println("playerB har:" + playerBScore);
		}

	}

	/**
	 * metoden r�cknar antal v�gstryckor f�r player
	 * @ param id f�r bilen som passerar en tejp
	 */
	public void countupPlayer(int id) {
		if(id==0) {	
			playerADistance = playerADistance + 15 ;
			System.out.println("playerA har antalstryckor:" + playerADistance);
		}else if(id==1) {
			playerBDistance = playerBDistance + 15 ;
			System.out.println("playerA har antalstryckor:" + playerBDistance);

		}
	}

	/**
	 * metoden som best�mmer vem som vinner
	 */

	public String winner(){

		if ((playerAScore > playerBScore) || (playerADistance > playerBDistance)) {

			return(" The winner is playerA ");

		}else if ((playerAScore < playerBScore) || (playerADistance < playerBDistance)){

			return(" The winner is playerB ");

		}

		return("There is no winner");

	}
}



