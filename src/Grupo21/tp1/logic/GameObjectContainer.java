package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer {
	
	
	private Mario mario;
	private List<GameObject> gameObjects;
	private List<GameObject> gameObjectsPending;
	
	public GameObjectContainer(){
		gameObjects = new ArrayList<>();
		gameObjectsPending = new ArrayList<>();
	}
	
	public void add(Land land) {
		this.gameObjects.add(land);
	}
	
	public void add(Goomba goomba) {
		this.gameObjects.add(goomba);
	}
	public void add(ExitDoor exit) {
		this.gameObjects.add(exit);
	}
	
	public void add(Mario mario) {
		if (this.mario != null) {
			gameObjects.remove(this.mario);
		}
		this.mario = mario;
		this.gameObjects.add(mario);
	}

	public void add(GameObject obj) {
		this.gameObjects.add(obj);
	}
	
	
	public void update() {
		
		for (GameObject obj : gameObjects) {
			obj.update();
			if (obj.isAlive()) doInteraction(obj);
		}
		
		// añadir los objetos nuevos añadidos en ejecuccion mientras se hace update/action
		// evitamos java.util.ConcurrentModificationException al añadir por ejemplo mushroom
		if (!gameObjectsPending.isEmpty()) {
	        gameObjects.addAll(gameObjectsPending);
	        gameObjectsPending.clear();
	    }
		
		//Limpiamos los elementos muertos
		clean();
		
	}
	
	private void clean() {		
		gameObjects.removeIf(obj -> !obj.isAlive());
	}

	
	public void addObjectFactory(GameObject obj) {
		
		for (GameObject gameObject : gameObjects) {
			if(gameObject.isSolid() && obj.isInPosition(gameObject)) return;
		}
		this.gameObjects.add(obj);
	}
	
	
	// para la lista de pendientes
	public void addPending(GameObject obj) {
		this.gameObjectsPending.add(obj);
	}
	
	
	public String positionToString(Position pos) {
		String ptoString = "";
		for (GameObject obj : gameObjects) {
			if (obj.isInPosition(pos)) {
				ptoString += obj.getIcon();
			}
		}
		if (ptoString != "") return ptoString;
		
		return Messages.EMPTY;
	}
	
	
	public boolean isSolid(Position pos) {
		for (GameObject obj : gameObjects) {
			if (obj.isInPosition(pos) && obj.isSolid()) {return true;}
		}
		return false;
	}
	
	public void doInteraction(GameObject gobj) {
		for (GameObject obj : gameObjects) {
			gobj.interactWith(obj);
			// comprobar que no este zombie
			if (!gobj.isAlive()) return;
		}
	}
	
	public void addAction(Action act) {
		for (GameObject obj : gameObjects) {
			if (!obj.isSolid() && obj.isAlive()) {
				// NO hace nada sino es mario.
				obj.addAction(act);
			}
		}
	}
}
