package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba {

	private Position pos;
	private Game game;
	
	public Goomba(Position pos){
		this.pos = pos;
	}

	public Goomba(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
	}

	public Position getPos() {
		return pos;
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}

	/*
	public void setPos(Position pos) {
		this.pos = pos;
	}*/
}
