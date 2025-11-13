package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	//private Position pos;
	
	public ExitDoor(Position pos){
		super(pos);
		//this.pos = pos;
	}
	
	public ExitDoor() {
		
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
	
}
