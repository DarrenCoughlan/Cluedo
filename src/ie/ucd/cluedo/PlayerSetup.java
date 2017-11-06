package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;
import ie.ucd.cluedo.Player;
import ie.ucd.cluedo.enums.SuspectCards; 

/**
 * Functionality to add the players to the game.
 * Here, we also assign cards randomly to those players and assign a pawn to each player.
 * @author Sam
 */
public class PlayerSetup {
	private String[] players;
	ArrayList<Player> playerNames;
	SuspectCards suspects;
	Cards cards;
	
//	When we create a new PlayerSetup object, we create a new list of cards and an array list of players.
	public PlayerSetup() {
		cards = new Cards();
		playerNames = new ArrayList<Player>();
	}
	
	public String[] getPlayers() {
		return players;
	}
	
//	TODO Move this to a GUI implementation
//	Adds a number of players via user input in the console
	public void addPlayers() {
		System.out.println("How many players are going to play? [3-6]");
		Scanner numberOfPlayersScanner = new Scanner(System.in);
		while(!numberOfPlayersScanner.hasNextInt()) {        					//Keep asking for a number
			System.out.println("Please enter a number between 3-6:");
			numberOfPlayersScanner.next();
		}
		
		int numPlayers = numberOfPlayersScanner.nextInt();
		while(numPlayers < 3) {													//Simple error checking for number of players
			System.out.println("There must be at least 3 players, enter a number between 3-6: ");
			numPlayers = numberOfPlayersScanner.nextInt();
		}
		
		System.out.println("Enter all " + numPlayers + " player names here: ");
		String[] playerNames = new String[numPlayers];

		Scanner playersScanner = new Scanner(System.in);
		for(int i = 0; i < numPlayers; i++) {									//Add all player names to an array
			playerNames[i] = (playersScanner.next());
		}
		
		numberOfPlayersScanner.close();											//Close input scanners
		playersScanner.close();
		
		players = playerNames;
	}	
	
//	Randomly assigns each player with cards, gives each player a pawn card name.
	public ArrayList<Player> setupPlayers() {
		cards.distributeCards(players.length);
		for(int i = 0; i < players.length; i++ ) {
			//TODO Maybe change this to a random pawn instead
			playerNames.add(new Player(players[i], SuspectCards.values()[i].getSuspect(), cards.getPlayerCards().get(i))); 
		}
		return playerNames;
	}
	
	
	//Temporary main method to test PlayerSetup class.
//	public static void main(String[] args) {
//		PlayerSetup setupPlayers = new PlayerSetup();
//		setupPlayers.addPlayers();
//		ArrayList<Player> players = new ArrayList<Player>();
//
//		players = setupPlayers.setupPlayers();
//		for(Player p : players) {
//			System.out.println("\nPlayer: " + p.getName() + ", Pawn: " +  p.getPawn());
//			System.out.println("Cards: " + p.getCards());
//		}
//	}
	
}
