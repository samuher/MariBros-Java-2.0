package tp1.logic;

/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private final int col;
	private final int row;

	//TODO fill your code
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public boolean equals(Position p) {
		return this.col == p.col && this.row == p.row;
	}
	
	
	public Position moved(Action a) {
	    return new Position(this.row + a.getY(), this.col + a.getX());
	}
	
	public boolean isVacio(Position p) {
		return p.row >= Game.DIM_Y;
	}
	
	public boolean isLateral(Position p) {
		return p.col > Game.DIM_X || p.col < 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.col + "," + this.row + ")";
	}

}
