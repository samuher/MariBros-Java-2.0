package tp1.logic.gameobjects;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class GameObject implements GameItem { // TODO 

	protected Position pos; // If you can, make it private.
	//protected boolean alive;
	protected GameWorld game; 
	
	protected String NAME;
	protected String SHORTCUT;
	
	public GameObject(GameWorld game, Position pos) {
		//this.alive = true;
		this.pos = pos;
		this.game = game;
	}
	
	
	public GameObject(Position pos) {
		this.pos = pos;
	}
	
	public GameObject() {
		
	}
	
	public boolean isInPosition(Position p) {
		// TODO fill your code here, it should depends on the status of the object
		return true;
	}
	
	public 	boolean isAlive() {return true;};
	
	// TODO implement and decide, Which one is abstract?
	// public boolean isSolid()
	public void update() {
		
	}
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		// TODO Auto-generated method stub
		this.pos = this.pos.moved(dir); 
	}
	public GameObject parse(String objWords[], GameWorld game) {
		// addGameObject objectos sencillos esto es para land, exit door, goomba y los nurvos objrtos. todo menos mario
		// x = 0, y= 1, n or sh = 2
		// comprobar que es el 
		System.out.println(this.NAME);
		System.out.println(this.SHORTCUT);
		if(objWords[2].toLowerCase().equals(this.NAME) || objWords[2].toLowerCase().equals(this.SHORTCUT)) {
			System.out.println("entra aqui");
			Position p = new Position(Integer.parseInt(objWords[0]), Integer.parseInt(objWords[1]));
			// inicializr y devolver
			this.pos = p;
			System.out.println(p.toString());
			this.game = game;
			return this;
		}
		return null;
	}
	
	
	@Override
		public boolean interactWith(GameItem item) {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
		public boolean isSolid() {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
		public boolean receiveInteraction(ExitDoor obj) {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
		public boolean receiveInteraction(Goomba obj) {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
		public boolean receiveInteraction(Land obj) {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
		public boolean receiveInteraction(Mario obj) {
			// TODO Auto-generated method stub
			return false;
		}
	@Override
	public boolean receiveInteraction(MushRoom obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean receiveInteraction(Box obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
