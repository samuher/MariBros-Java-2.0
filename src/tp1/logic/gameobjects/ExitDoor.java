package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	//private Position pos;
	
	public ExitDoor(Position pos){
		super(pos);
		//this.pos = pos;
	}
	
	public ExitDoor(GameWorld game, Position pos) {
		super(game, pos);
	}

	public ExitDoor() {
		super();
		this.NAME = Messages.EXITDOOR_NAME;
		this.SHORTCUT = Messages.EXITDOOR_SHORTCUT;
	}

	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	public boolean isInPosition(Position p) {
		return (this.pos.equals(p));
	}
	
	public boolean isInPosition(Mario m) {
		return m.isInPosition(this.pos);
	}
	
	@Override
	public boolean receiveInteraction(Mario obj) {
		
		//game.marioExited();
		obj.receiveInteraction(this);
		return true;
	}

	@Override
	public boolean receiveInteraction(MushRoom mushRoom) {
		
		return false;
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		
		return new ExitDoor(game, pos);
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
