package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.view.Messages;
import tp1.logic.Position;

public class Mario {

	//TODO fill your code
	
	/**
	 *  Implements the automatic update	
	 */
	
	private Position pos;
	private boolean big;
	private boolean right = false;
	private boolean left = false;
	private boolean stop = true;
	private Game game;
	
	
	public Mario(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
		
	}

	public void update() {
		//TODO fill your code
		//actualiza a Mario ( y quizas su entorno)
	}
	
	public String getIcon() {
		/*
		if (big){
			ret 
		}
		*/
		// devuelve el icono, segun su direccion
		
		if (this.stop) {
			return Messages.MARIO_STOP; 
		} else if (this.right) {
			return Messages.MARIO_RIGHT;
		} else if (this.left) {
			return Messages.MARIO_LEFT;
		}
		System.out.println("Eror direccion Mario");
		
		return Messages.MARIO_STOP;
	}
	
	public String toString() {
		// devuelve una representación de Mario, ej: Mario grande situado en la posicion (1,2), parado
		return null;
		
	}
	
	public boolean isInPosition (Position p) {
		return (p.getCol() == this.pos.getCol() && p.getRow() == this.pos.getRow());
	}
	
	public int getCol() {
		return pos.getCol();
	}
	
	public int getRow() {
		return pos.getRow();
	}
	
	
	
}
