package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject {
	
	private boolean full = true;

	public Box(GameWorld game, Position pos) {
		super(game, pos);
		// TODO Auto-generated constructor stub
	}

	public Box() {
		super();
		this.NAME = Messages.BOX_NAME;
		this.SHORTCUT = Messages.BOX_SHORTCUT;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {return true;}
	
	public boolean isFull() {
		return this.full;
	}
	
	public GameObject parse(String objWords[], GameWorld game) {

		// comprobacion de mario 
		if (objWords[1].toLowerCase().equals(this.NAME)|| objWords[1].toLowerCase().equals(this.SHORTCUT)) {
			Position p = Position.parsePosition(objWords[0]);
			if (p == null) return null;
			
			// añadimos el juego
			this.game = game;
			// añadimos la posicion 
			this.pos = p;
			
			// diferencia entre full y empty
			if (objWords.length > 3) {
				// direccion si existe
				switch (objWords[3]) {
				case "full", "f" -> {
					// esto podriamos omitirlo
					this.full = true;
				}
				case "empty", "e" -> {
					this.full = false;
				}
				default -> {return null;}}
			}
			return this;
		}
		return null;
	}

	@Override
	public boolean receiveInteraction(MushRoom mushRoom) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean receiveInteraction(Mario m) {
		// TODO Auto-generated method stub
		if(isFull()) {
			game.addPoints(50);
			game.addMushroom(this.pos);
			this.full = false;
			m.receiveInteraction(this);		
		}
		return true;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return this.full ? Messages.BOX : Messages.EMPTY_BOX;
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		// TODO Auto-generated method stub
		return new Box(game, pos);
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
