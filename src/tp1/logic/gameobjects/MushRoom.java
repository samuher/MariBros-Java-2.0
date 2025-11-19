package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MushRoom extends MovingObject {

	public MushRoom(GameWorld game, Position pos) {
		super(game, pos);
		this.avanza = false;
		this.NAME = Messages.MUSHROOM_NAME;
		this.SHORTCUT = Messages.MUSHROOM_SHORTCUT;
		
	}

	public MushRoom() {
		
		super();
		this.NAME = Messages.MUSHROOM_NAME;
		this.SHORTCUT = Messages.MUSHROOM_SHORTCUT;
	}
	
	@Override
	public void update() {
		
		automaticMovement();
	}
	
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {
		//System.out.println("bbsita");
		if (isAlive()) {
			dead();
			mario.receiveInteraction(this);
		}
		
		return true;
	}
	
	@Override
	public String getIcon() {
		
		return Messages.MUSHROOM;
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		
		return new MushRoom(game, pos);
	}
	
	public GameObject parse(String objWords[], GameWorld game) {
		
		// comprobacion goomba
		if (objWords[1].toLowerCase().equals(this.NAME) || objWords[1].toLowerCase().equals(this.SHORTCUT)) {
			Position p = Position.parsePosition(objWords[0]);
			if (p == null) return null;
			
			MushRoom m = new MushRoom(game,p);
			
			// direccion goomba
			if (objWords.length > 2) {
				// direccion si existe
				switch (objWords[2].toLowerCase()) {
				case "right", "r" -> {
					m.avanza = false;
				}
				case "left", "l" -> {
					m.avanza = true;
				}
				default -> {return null;}
				}	
			}
			return m;
			
		}
		return null;
		
	}
	

}
