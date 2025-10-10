package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.ActionList;
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
	private boolean avanza = false;
	private boolean cayendo = false;
	private Game game;
	
	private ActionList actlist;
	
	public Mario(Game game, Position position) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = position;
		this.actlist = new ActionList();
		
	}

public void update() {
	
		Action dir;
		if (actlist.anyActions()) {
			while(actlist.anyActions()) {
				dir = actlist.nextAction();
				Position lateral = this.pos.moved(dir);
				if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
					continue;
				}
				//this.pos = this.pos.moved(dir);
				if (dir == Action.LEFT ||dir == Action.RIGHT ) {
					this.left = !this.left;
					this.right = !this.right;
					this.stop = false;
				} else if (dir == Action.DOWN) {
					this.left = false;
					this.right = false;
					this.stop = true;
					Position suelo = this.pos.moved(Action.DOWN);
					caida(suelo);
					//this.cayendo = true;
				}
				
				this.pos = this.pos.moved(dir);
			}
			game.doInteractionsFrom(this);
			return;
		}
		
		Position suelo = this.pos.moved(Action.DOWN);
		if (caida(suelo)) return;
		dir = avanza ? Action.LEFT : Action.RIGHT;
		Position lateral = this.pos.moved(dir);
		if (lateral.isLateral(lateral) || game.isSolid(lateral)) {
			avanza = !avanza;
			//dir = avanza ? Action.LEFT : Action.RIGHT;
			//this.pos = this.pos.moved(dir);
		} else {
			this.pos = this.pos.moved(dir);
		}
		if (avanza) {
			this.left = true;
			this.right = false;
			this.stop = false;
		} else {
			this.left = false;
			this.right = true;
			this.stop = false;
		}
		suelo = this.pos.moved(Action.DOWN);
		caida(suelo);
		game.doInteractionsFrom(this);
		
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
		return (this.pos.equals(p));
	}
	public boolean isInPosition (Goomba g) {
		return g.isInPosition(this.pos);
	}
	
	public void addAction(Action act) {
		this.actlist.add(act);
	}
	
	public boolean caida(Position suelo) {
		if (!game.isSolid(suelo)) {
			while (!game.isSolid(suelo)){
				if (this.pos.isVacio(suelo)) {
					//this.dead = true;
					game.marioDead();
					return true;
				}
				this.pos = suelo;
				//this.pos = this.pos.moved(Action.DOWN);
				suelo = this.pos.moved(Action.DOWN);
				
			}
			this.cayendo = true;
			return true;
		}
		//this.cayendo = false;
		return false;
		
	}
	
	public boolean interactWith(ExitDoor other) {
		return other.isInPosition(this);
	}
	
	public boolean interactWith(Goomba goomba) {
		
		if(isInPosition(goomba)) {
			game.addPoints(100);
			goomba.receiveInteraction(this);
			if (!cayendo) {
				if (big) {
					this.big = false;
				} else {
					game.marioDead();
				}	
			}
		}
		
		return false;
	}
	
}
