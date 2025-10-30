package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	private Position pos;
	
	public ExitDoor(Position pos){
		super(pos);
		//this.pos = pos;
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
	/*
	public void setPos(Position pos) {
		this.pos = pos;
	}*/
	
	public void openDoor() {
		this.pos = this.pos.moved(Action.LEFT);
	}
}
