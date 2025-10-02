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
	
	public int getCol() {
		return this.pos.getCol();
	}
	
	public int getRow() {
		return this.pos.getRow();
	}
	
	
	/*
	public void add(Goomba goomba) {
		
	}*/
	
	
}
