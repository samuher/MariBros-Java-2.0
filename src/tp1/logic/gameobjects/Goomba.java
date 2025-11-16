package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject{

	//private Position pos;
	//private Game game;
	//private boolean avanza= true;
	private boolean dead;
	private boolean alive;
	
	
	public Goomba(Game game, Position position) {
		// TODO Auto-generated constructor stub
		super(game, position);
		this.NAME = "goomba";
		this.SHORTCUT = "g";
	}
	
	public Goomba() {
		
	}
	
	public GameObject parse(String objWords[], GameWorld game) {
		
		// comprobacion goomba
		if (objWords[2].toLowerCase() == this.NAME || objWords[2].toLowerCase() == this.SHORTCUT) {
			
			Position p = new Position(Integer.parseInt(objWords[0]), Integer.parseInt(objWords[1]));
			
			// añadimos el juego
			this.game = game;
			// añadimos la posicion 
			this.pos = p;
			
			if (objWords.length > 3) {
				// direccion si existe
				switch (objWords[3]) {
				case "right", "r" -> {
					this.avanza = false;
				}
				case "left", "l" -> {
					this.avanza = true;
				}
				default -> {return null;}
			}
			}
		}
		return null;
		
	}
	

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
		automaticMovement();
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
	
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}

}
