package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MushRoom extends MovingObject {

	public MushRoom(GameWorld game, Position pos) {
		super(game, pos);
		this.avanza = false;
		// TODO Auto-generated constructor stub
	}

	public MushRoom() {
		// TODO Auto-generated constructor stub
		super();
		this.NAME = Messages.MUSHROOM_NAME;
		this.SHORTCUT = Messages.MUSHROOM_SHORTCUT;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return Messages.MUSHROOM;
	}

}
