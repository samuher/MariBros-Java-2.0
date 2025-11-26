package tp1.logic;

public interface GameStatus {
	public String positionToString(int col, int row);
	public boolean playerWins();
	public boolean playerLoses();
	public int remainingTime();
	public int points();
	public int numLives();
	public String toString();
}
