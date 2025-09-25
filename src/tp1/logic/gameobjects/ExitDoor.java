package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
	
	private Position pos;
	
	public ExitDoor(Position pos){
		this.pos = pos;
	}

	public Position getPosition() {
		return pos;
	}
	
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	
	/*
	public void setPos(Position pos) {
		this.pos = pos;
	}*/
}
