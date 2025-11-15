package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;


public class Land extends GameObject{
	//private Position pos; ya esta en la clase padre
	
	public Land(Position position) {
		// TODO Auto-generated constructor stub
		super(position);
		this.NAME = "land";
		
		//this.pos = position;
	}
	
	public Land() {
		
	}

	public String getIcon() {
		return Messages.LAND;
	}
	
	public boolean isInPosition(Position p) {
		return (this.pos.equals(p));
	}
	public boolean isSolid() {return true;}
		
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract = item.isInPosition(this.pos);
		if(canInteract) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}
}
