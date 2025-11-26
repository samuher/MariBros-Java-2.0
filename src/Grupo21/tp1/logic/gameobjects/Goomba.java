package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject{
	
	public Goomba(GameWorld game, Position position) {
		super(game, position);
		this.NAME = Messages.GOOMBA_NAME;
		this.SHORTCUT = Messages.GOOMBA_SHORTCUT;
	}
	
	public Goomba() {
		super();
		this.NAME = Messages.GOOMBA_NAME;
		this.SHORTCUT = Messages.GOOMBA_SHORTCUT;
	}
	
	public GameObject parse(String objWords[], GameWorld game) {
		
		// comprobacion goomba
		if (objWords[1].toLowerCase().equals(this.NAME) || objWords[1].toLowerCase().equals(this.SHORTCUT)) {
			Position p = Position.parsePosition(objWords[0]);
			if (p == null) return null;
			
			// añadimos el juego		
			// añadimos la posicion 
			Goomba g = new Goomba(game, p);
			
			// direccion goomba
			if (objWords.length > 2) {
				// direccion si existe
				switch (objWords[2].toLowerCase()) {
				case "right", "r" -> {
					g.avanza = false;
				}
				case "left", "l" -> {
					g.avanza = true;
				}
				default -> {return null;}
			}
			}
			return g;
		}
		return null;
		
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		
		return null;
	}
	

	public boolean isInPosition (Position p) {
		return (this.pos.equals(p));
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
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
		if (isAlive() && other.isAlive()) {
			game.addPoints(100);
			dead();
			other.receiveInteraction(this);
		}
		return true;
	}
	
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			item.receiveInteraction(this);
			return false;
		}
		return canInteract;
	}

}
