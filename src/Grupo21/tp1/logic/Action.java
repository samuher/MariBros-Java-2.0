package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);
	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static Action parseAction(String word) {
		// direccion si existe
		switch (word.toLowerCase()) {
		case "right", "r" -> {
			return RIGHT;
		}
		case "left", "l" -> {
			return LEFT;
		}
		case "stop", "s" -> {
			return STOP;
		}
		default -> {
			return null;}}
	}
	
	
	
}
