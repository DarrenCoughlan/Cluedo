package ie.ucd.cluedo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Cluedo {

	
	public Cluedo() throws FileNotFoundException {
//		Add & setup players
		Cards deck = new Cards();
		PlayerSetup setup = new PlayerSetup(deck.getRemainingCards());
		setup.setupPlayers();
		ArrayList<Player> players = setup.getPlayers();
		
//      Create a customized notebook for each player		
		for(Player p : players) {
			Notebook nb = new Notebook(p);
		}
		
//		Below is temporary test code & will be removed soon.
		for(Player p : players) {
			System.out.println("\nPlayer: " + p.getName() + ", Pawn: " +  p.getPawn());
			System.out.println("Cards: " + p.getCards());
		}
		
//		Board initialisation
		CluedoBoard myBoard = new CluedoBoard();
		myBoard.initialiseBoard(players);
		myBoard.printBoard();

//		Give each player a turn
		//TODO This will have to keep looping until some boolean endGame == 1
		//TODO After each player moves, they get to do something [Accusation, Hypothesis, SecretPassage, Nothing etc]
		for(Player p : players) {
			myBoard.playerMove(p);
		}
		
		System.out.println("Game demo over...");
		System.exit(0);
	}
		
		
	
}
