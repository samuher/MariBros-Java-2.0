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
	private boolean right;
	private boolean left;
	private Game game;
	
	
	public Mario(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
	}

	public void update() {
		//TODO fill your code
	}
	
	public String getIcon() {
		/*
		if (big){
			ret 
		}
		*/
		
		
		return Messages.MARIO_STOP;
	}
	
	public Position getPosition() {
		return this.pos;
	}
}
