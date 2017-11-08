package ie.ucd.cluedo;

/**
 * Create dice
 * @author Darren
 */
public class Dice {
	
	//Returns a random integer between 1-12 to simulate two dice being thrown.
	public int roll() {
		return 1 + (int) (Math.random() * 12.0);
	}
	
	
	// Main method to test the above Dice class code
	public static void main(String[] args) {
		Dice diceys = new Dice();
		for(int i = 0; i < 10; i++) {
			System.out.println(diceys.roll());
		}
	}

}
