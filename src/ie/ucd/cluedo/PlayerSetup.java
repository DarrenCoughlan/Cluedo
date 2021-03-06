package ie.ucd.cluedo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import ie.ucd.cluedo.Player;
import ie.ucd.cluedo.enums.SuspectCards; 

/**
 * Functionality to add the players to the game.
 * Here, we also assign cards randomly to those players and assign a pawn to each player.
 * @author Sam
 */
public class PlayerSetup {
	private String[] playerNames;
	static ArrayList<Player> players;
	SuspectCards suspects;
	private ArrayList<String> remainingCards = new ArrayList<String>();
	private ArrayList<ArrayList<String>> playerCards = new ArrayList<ArrayList<String>>();
	
	//When we create a new PlayerSetup object, we create a new list of cards and an array list of players.
	public PlayerSetup(ArrayList<String> remainingCards) {
		players = new ArrayList<Player>();
		addPlayers();
		this.remainingCards = remainingCards;
	}
	
	public String[] getPlayerNames() {
		return playerNames;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	//Removes player from game if they make an incorrect accusation
	public static void deletePlayer(Player player) {
		players.remove(player);
	}
	
	public void setPlayers(ArrayList<Player> newPlayers) {
		players = newPlayers;
	}
	
	public ArrayList<ArrayList<String>> getPlayerCards(){
		return playerCards;
	}
	
	//Adds a number of players via user input in the console
	public void addPlayers() {
		System.out.println("How many players are going to play? [3-6]");
		Scanner scanner = new Scanner(System.in);
		
		//Keep asking for a number
		while(!scanner.hasNextInt()) {        									
			System.out.println("Please enter a number between 3-6:");
			scanner.next();
		}
		
		int numPlayers = scanner.nextInt();
		
		//Error checking for number of players
		while(numPlayers < 3 || numPlayers > 6) {													
			System.out.println("There must be between 3 and 6 players inclusive: ");
			numPlayers = scanner.nextInt();
		}
		
		System.out.println("Enter all " + numPlayers + " player names here: ");
		String[] players = new String[numPlayers];
		
		//Add all player names to an array
		for(int i = 0; i < numPlayers; i++) {									
			players[i] = (scanner.next());
		}
		
		playerNames = players;
	}
	
	// Distributes the cards among the players by storing each arraylist in playerCards arraylist
	public void distributeCards(int numPlayers) {
		Collections.shuffle(remainingCards);
		int k = numPlayers;
		for(int i=0; i<numPlayers; i++) {
			int x = (int) Math.floor(remainingCards.size()/k);
			playerCards.add(new ArrayList<String>(remainingCards.subList(0, x)));
			for(int j=0; j<x; j++) {
				remainingCards.remove(0);
			}
			k-=1;
		}
	}
	
    // Randomly assigns each player with cards, gives each player a pawn card name.
	public void setupPlayers() throws FileNotFoundException {
		this.distributeCards(playerNames.length);
		for(int i = 0; i < playerNames.length; i++ ) {
			players.add(new Player(playerNames[i], SuspectCards.values()[i].getSuspect(), this.getPlayerCards().get(i))); 
		}
	}
	
}
