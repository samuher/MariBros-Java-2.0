package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
	
	private boolean avanza;

	public MovingObject(Game game, Position pos) {
		super(game, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected boolean automaticMovement(boolean avanza) {
		if (caida(this.pos.moved(Action.DOWN))) return false;
		Action dir = avanza ? Action.LEFT : Action.RIGHT;
		Position lateral = this.pos.moved(dir);
		//if (lateral.isLateral(lateral) || game.isSolid(lateral))
		if(isNextToLateral(dir)|| isNextToSolid(dir)) {
			avanza = !avanza;
			leftToRight(avanza);
			//dir = avanza ? Action.LEFT : Action.RIGHT;
			//this.pos = this.pos.moved(dir);
		} else {
			//this.pos = this.pos.moved(dir);
			movimientoUnitario(avanza, dir);
		}
		return false;
	}
	
	public boolean isNextToSolid(Action dir) {
		return false;
	}
	
	public boolean isNextToLateral(Action dir) {
		//return game.isLateral(this.pos.moved(dir));
		return false;
	}
	
	protected void movimientoUnitario(boolean avanza, Action dir) {
		this.pos = this.pos.moved(dir);
	}
	
	public void leftToRight(boolean avanza) {
		
	}
	
	public boolean caida(Position suelo) {
		if(!game.isSolid(suelo)) {
			if (suelo.isVacio(suelo)) {
				deadMovingObject();
				return true;
			}
			this.pos = suelo;
			return true;
		}
		return false;
	}
	
	
	
	
	protected void deadMovingObject() {
		// TODO Auto-generated method stub
	}

}
