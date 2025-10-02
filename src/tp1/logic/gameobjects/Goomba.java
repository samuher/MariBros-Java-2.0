package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba {

	private Position pos;
	private Game game;
	private boolean avanza= true;
	private boolean dead;
	
	public Goomba(Position pos){
		this.pos = pos;
	}

	public Goomba(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
	}

	public int getCol() {
		return pos.getCol();
	}
	
	public int getRow() {
		return pos.getRow();
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public void update() {
		
		if(game.isSolid(pos)) {
			this.avanza = false;
		}
		
		
		Position pisa = new Position(pos.getRow(), pos.getCol()-1);
		if (game.isSolid(pisa)) {
			if (this.avanza) {
				this.pos = new Position(pos.getRow()-1, pos.getCol());
			} else {
				this.pos = new Position(pos.getRow()+1, pos.getCol());
			}
			
		} else {
			// caida hasta final del tablero
			// ¿como encuentro final del tablero?
		}
		
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	/*
	public void setPos(Position pos) {
		this.pos = pos;
	}*/
}
