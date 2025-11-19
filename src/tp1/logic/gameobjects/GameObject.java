package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameObjectContainer;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem { 

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
		return (this.pos.equals(p));
	}
	
	public boolean isInPosition(GameObject g) {
		return (this.pos.equals(g.pos));
	}
	
	public void addAction(Action act) {
		// NO hace nada sino es mario.
	}
	public boolean isAlive() {return true;};
	public void dead() {};
	
	
	// public boolean isSolid()
	public void update() {
		
	}
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		
		this.pos = this.pos.moved(dir); 
	}
	public GameObject parse(String objWords[], GameWorld game) {
		// addGameObject objectos sencillos esto es para land, exit door, goomba y los nurvos objrtos. todo menos mario
		// x = 0, y= 1, n or sh = 2
		// comprobar que es el 
		if(objWords[1].toLowerCase().equals(this.NAME) || objWords[1].toLowerCase().equals(this.SHORTCUT)) {
			Position p = Position.parsePosition(objWords[0]);
			if (p == null) return null;
			// inicializr y devolver
			this.pos = p;
			this.game = game;
			return createInstance(game, p);
		}
		return null;
	}
	
	// se sobrescribe en cada clase hija para poder hacer funcionar metodo parse
	protected abstract GameObject createInstance(GameWorld game, Position pos);
	
	
	@Override
		public boolean interactWith(GameItem item) {
			
			return false;
		}
	@Override
		public boolean isSolid() {
			
			return false;
		}
	@Override
		public boolean receiveInteraction(ExitDoor obj) {
			
			return false;
		}
	@Override
		public boolean receiveInteraction(Goomba obj) {
			
			return false;
		}
	@Override
		public boolean receiveInteraction(Land obj) {
			
			return false;
		}
	@Override
		public boolean receiveInteraction(Mario obj) {
			
			return false;
		}
	@Override
	public boolean receiveInteraction(MushRoom obj) {
		
		return false;
	}
	
	@Override
	public boolean receiveInteraction(Box obj) {
		
		return false;
	}
	
	public void add(GameObjectContainer gameObjects) {
		gameObjects.add(this);
	}
	
}
