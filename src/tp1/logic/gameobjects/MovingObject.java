package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
	
	protected boolean avanza = true;
	private boolean alive = true;

	public MovingObject(GameWorld game, Position pos) {
		super(game, pos);
		// TODO Auto-generated constructor stub
	}
	
	public MovingObject() {
		
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// objetos que tienen vida
	// no estan en la clase super ya que no viven
	public boolean isAlive() {return alive;}
	public void dead(){
		System.out.println("ha muerto un objeto");
		this.alive = false;
	}
	
	protected boolean automaticMovement() {
		
		if (caida(this.pos.moved(Action.DOWN))) return false;
		Action dir = this.avanza ? Action.LEFT : Action.RIGHT;
		Position lateral = this.pos.moved(dir);
		//if (lateral.isLateral(lateral) || game.isSolid(lateral))
		if(isNextToLateral(dir)|| isNextToSolid(dir)) {
			this.avanza = !this.avanza;
			leftToRight(this.avanza);
			//dir = avanza ? Action.LEFT : Action.RIGHT;
			//this.pos = this.pos.moved(dir);
		} else {
			//this.pos = this.pos.moved(dir);
			movimientoUnitario(this.avanza, dir);
		}
		return false;
	}
	
	public boolean isNextToSolid(Action dir) {
		return game.isSolid(this.pos.moved(dir));
	}
	
	public boolean isNextToLateral(Action dir) {
		//return game.isLateral(this.pos.moved(dir));
		return this.pos.isLateral(this.pos);
	}
	
	protected void movimientoUnitario(boolean avanza, Action dir) {
		this.pos = this.pos.moved(dir);
	}
	
	public void leftToRight(boolean avanza) {
		
	}
	
	public boolean caida(Position suelo) {
		if(!game.isSolid(suelo)) {
			if (suelo.isVacio(suelo)) {
				dead();
				return true;
			}
			this.pos = suelo;
			return true;
		}
		return false;
	}	
	

}
