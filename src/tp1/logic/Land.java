package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.view.Messages;

public class Land extends GameObject{
	//private Position pos; ya esta en la clase padre
	
	public Land(Position position) {
		// TODO Auto-generated constructor stub
		super(position);
		//this.pos = position;
	}

	public String getIcon() {
		return Messages.LAND;
	}
	
	public boolean isInPosition(Position p) {
		return (this.pos.equals(p));
	}
	
	
}
