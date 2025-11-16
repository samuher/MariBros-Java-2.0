package tp1.logic.gameobjects;

import tp1.logic.Action;
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
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		//game.marioExited();
		obj.receiveInteraction(this);
		return true;
	}

	@Override
	public boolean receiveInteraction(MushRoom mushRoom) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		// TODO Auto-generated method stub
		return new ExitDoor(game, pos);
	}
	
}
