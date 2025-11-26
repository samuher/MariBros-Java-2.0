package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;


public class Land extends GameObject{
	
	public Land(Position position) {
		super(position);
	}
	
	public Land(GameWorld game, Position pos) {
		super(game, pos);
	}

	public Land() {
		super();
		this.NAME = Messages.LAND_NAME;
		this.SHORTCUT = Messages.LAND_SHORTCUT;
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
		return false;
	}

	@Override
	public boolean receiveInteraction(MushRoom mushRoom) {
		
		return false;
	}
	
	@Override
	protected GameObject createInstance(GameWorld game, Position pos) {
		return new Land(game, pos);
	}

	@Override
	public void dead() {
		return;
	}

	@Override
	public void update() {
		return;
	}
	
}
