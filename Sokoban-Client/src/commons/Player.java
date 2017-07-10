package commons;

/**
 * Sets a player in the game
 * @see public Player() Sets the mark of the player 'A'
 * @see public Player(position pos) Sets the mark of the player 'A' and the location of the player
 */
public class Player extends TypeA implements moveAble {
	
	//De'f C'tor
	/**
	 * Sets the mark of the player 'A'
	 */
		public Player() {
			super('A',null);
	}		
	//C'tor
	/**
	 * Sets the mark of the player 'A' and the location of the player
	 * @param pos the location of the player
	 */
	public Player(position pos) {
		super('A',pos);
		
	}
	
}
