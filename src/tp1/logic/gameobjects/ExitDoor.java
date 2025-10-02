package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
	
	private Position pos;
	
	public ExitDoor(Position pos){
		this.pos = pos;
	}

	
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	public int getCol() {
		return pos.getCol();
	}
	
	public int getRow() {
		return pos.getRow();
	}
	
	
	
	
	/*
	public void setPos(Position pos) {
		this.pos = pos;
	}*/
}
