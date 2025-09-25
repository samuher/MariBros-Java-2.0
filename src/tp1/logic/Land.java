package tp1.logic;

import tp1.view.Messages;

public class Land {
	private Position pos;
	
	public Land(Position position) {
		// TODO Auto-generated constructor stub
		this.pos = position;
	}

	public String getIcon() {
		return Messages.LAND;
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	/*
	public void add(Goomba goomba) {
		
	}*/
	
	
}
