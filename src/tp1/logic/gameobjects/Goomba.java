package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject{

	//private Position pos;
	//private Game game;
	private boolean avanza= true;
	private boolean dead;
	private boolean alive;
	
	public Goomba(Game game, Position position) {
		// TODO Auto-generated constructor stub
		super(game, position);
		//this.game = game;
		//this.pos = position;
	}
	
	@Override
	public boolean isGoomba() {return true;}
	

	public boolean isInPosition (Position p) {
		return (this.pos.equals(p));
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public boolean caida(Position suelo) {
		if(!game.isSolid(suelo)) {
			if (suelo.isVacio(suelo)) {
				//this.dead = true;
				dead();
				return true;
			}
			this.pos = suelo;
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
		//suelo = this.pos.moved(Action.DOWN);
		//caida(suelo);
	}
	
	
	public boolean isNextToSolid(Action dir) {
		return game.isSolid(this.pos.moved(dir));
	}
	
	public boolean isNextToLateral(Action dir) {
		return this.pos.isLateral(this.pos.moved(dir));
	}
	
	
	
	public boolean isDead() {
		//return this.dead;
		return !isAlive();
	}
	
	public boolean receiveInteraction(Mario other) {
		//this.dead = true;
		//dead();
		if (isAlive()) {
			game.addPoints(100);
			dead();
			other.receiveInteraction(this);
		}
		
		//other.receiveInteraction(this);
		//game.cleanGoomba();
		return true;
	}
	
	public void deadMovingObject() {
		//this.dead = true;
		dead();
		//game.cleanGoomba();// Puede estar mal, posible borrado antes de update
	}
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}
	

}
