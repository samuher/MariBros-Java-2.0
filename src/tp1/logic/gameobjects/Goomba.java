package tp1.logic.gameobjects;

import tp1.logic.Action;
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

	public boolean isInPosition (Position p) {
		return (this.pos.equals(p));
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public boolean caida(Position suelo) {
		if (!game.isSolid(suelo)) {
			while (!game.isSolid(suelo)){
				if (this.pos.isVacio(suelo)) {
					this.dead = true;
					return true;
				}
				this.pos = suelo;
				//this.pos = this.pos.moved(Action.DOWN);
				suelo = this.pos.moved(Action.DOWN);
				
			}
			return true;
		}
		return false;
		
	}
	
	public void update() {
		
		Position suelo = this.pos.moved(Action.DOWN);
		if (caida(suelo)) return;
		Action dir = avanza ? Action.LEFT : Action.RIGHT;
		Position lateral = this.pos.moved(dir);
		if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
			avanza = !avanza;
			//dir = avanza ? Action.LEFT : Action.RIGHT;
			//this.pos = this.pos.moved(dir);
		} else {
			this.pos = this.pos.moved(dir);
		}
		suelo = this.pos.moved(Action.DOWN);
		caida(suelo);
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public boolean receiveInteraction(Mario other) {
		this.dead = true;
		return false;
	}
	

}
