/**
 * @author Connor Flint
 *
 * Class for a player object that can be added to QueueWAR
 */
public class Player implements Comparable<Player>{
	private int war;
	private String name;
	
	/**
	 * Creates a new player object given player's name, number of goals, number of assists, and 
	 * number of penalties.
	 * 
	 * @param name of player
	 * @param number of goals that player has scored
	 * @param number of assists that player has
	 * @param number of penalties player has
	 * @throws NullPointerException if name is null
	 * @throws IllegalArgumentException if goals, assists, or penalties is null
	 */
	public Player(String name, int goals, int assists, int penalties) throws NullPointerException,
	IllegalArgumentException{
		if (name == null) {
			throw new NullPointerException("Name can't be null!");
		}
		if (goals < 0) {
			throw new IllegalArgumentException("Goals can't be less than 0!");
		}
		if (assists < 0) {
			throw new IllegalArgumentException("Assists can't be less than 0!");
		}
		if (penalties < 0) {
			throw new IllegalArgumentException("Penalties can't be less than 0!");
		}
		
		this.name = name;
		this.war = Player.calculateWAR(goals, assists, penalties);
	}
	
	public static int calculateWAR(int goals, int assists, int penalties) {
		int warNum = goals + assists - penalties;
		return warNum;
	}
	
	
	/**
	 * Public getter method that returns WAR for given player
	 * 
	 * @return WAR for given Player object
	 */
	public int getWAR() {
		return this.war;
	}
	
	/**
	 * Defines string representation of a Player
	 * 
	 * @returns string representation of a Player
	 */
	public String toString() {
		String s = this.name + " (WAR: " + war + ")";
		return s;
	}
	
	/**
	 * Compares Player's WAR to a given Player
	 * 
	 * @returns int that represents difference in WAR in compared Players
	 */
	@Override
	public int compareTo(Player p) {
		return (war - p.getWAR());
	}
}
