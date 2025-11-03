package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer {
	//TODO fill your code
	private List<Land> landList;
	private Mario mario;
	private ExitDoor exit;
	private List<Goomba> goombas;
	private List<GameObject> gameObjects;
	//private List<GameObject> toRemove;
	
	public GameObjectContainer(){
		landList = new ArrayList<>();
		goombas = new ArrayList<>();
		gameObjects = new ArrayList<>();
		//toRemove = new ArrayList<>();
		// land = new Land[Game.DIM_X][Game.DIM_Y];
		
	}
	
	public void add(Land land) {
		this.landList.add(land);
	}
	
	public void add(Goomba goomba) {
		this.goombas.add(goomba);
	}
	public void add(ExitDoor exit) {
		this.exit = exit;
	}
	public void add(Mario mario) {
		this.mario = mario;
	}
	/*
	public Land[][] getLand(){
		return this.landList;
	}*/
	
	
	//update antiguo
	/*
	public void update() {
		//llamar a los metodos update de los objetos del tableroa
		// primero se actualiza mario y luego los goombas
		this.mario.update();
		if (this.mario.interactWith(exit) && !mario.isWin()) {
			//System.out.println("ha tocado la puerta");
			//exit.openDoor();
			mario.wins();
			//update();
		}
		
		//this.goombas.removeIf(Goomba :: isDead);
		//System.out.println("update");
		for (Goomba goomba : goombas ) {
			goomba.update();
		}
		doInteractionsFrom(mario);
		clean();
	}
	
	*/
	
	public void update() {
		
		//Primero actualizamos a Mario
		for(GameObject obj : gameObjects) {
			if(obj.isMario() && obj.isAlive()) {
				obj.update();
				break;
			}
		}
		
		
		//Mario interactua con la puerta
		GameObject mario = findMario();
		GameObject exit = findExitDoor();
		if(mario != null && exit != null) {
			mario.interactWith(exit);
		}
		
		
		//Luego se actualizan los goombas
		for(GameObject obj : gameObjects) {
			if(obj.isGoomba() && obj.isAlive() ) {
				obj.update();
				
				/*
				//Si muere durante update
				if(!obj.isAlive()) {
					toRemove.add(obj);
				}
				*/
			}
		}
		
		
		
		//Despues de esto Mario hace las interacciones con otros objetos
		if(mario != null) {
			for(GameObject obj : gameObjects) {
				if(obj != null && obj.isAlive() && obj.isGoomba()) {
					mario.interactWith(obj);
					obj.interactWith(mario);
					/*
					//Si muere durante la interaccion
					if(!obj.isAlive()) {
						toRemove.add(obj);
					}
					*/
				}
			}
		}
		
		//Limpiamos los elementos muertos
		clean();
	}
	
	public void clean() {
		/*
		for (Goomba goomba : goombas ) {
			if (goomba.isDead()) {
				goombas.remove(goomba);
				return;
			}
		}*/
		for (GameObject obj : gameObjects) {
			if (obj.isGoomba() && !obj.isAlive()) {
				gameObjects.remove(obj);
				clean();
			}
		}
		
		//goombas.removeIf(Goomba::isDead);
		//gameObjects.removeAll(toRemove);
	}
	
	public void add(GameObject obj) {
		this.gameObjects.add(obj);
	}
	
	
	public String positionToString(Position pos) {
		//int col = pos.getCol();
		//int row = pos.getRow();
		for (Land land : landList) {
			if (land.isInPosition(pos)) {
				return land.getIcon();			}
		}
		String gom = "";  
		for (Goomba goomba : goombas ) {
			if (goomba.isInPosition(pos) && !goomba.isDead()) {
				gom+= goomba.getIcon(); // permite dos goombas en la misma posición, como en los tests
			}
		}
		if (gom != "") return gom;
		
		
		String marexit = "";
		if (mario.isInPosition(pos)) {
			marexit+= mario.getIcon();
		}
		
		if (exit.isInPosition(pos)) {
			marexit+= exit.getIcon();
		}
		if (marexit != "") return marexit; 
		
		
		return Messages.EMPTY;
	}
	
	public boolean isSolid(Position pos) {
		for (Land land : landList) {
			if (land.isInPosition(pos)) {
				return true;}
		}
		return false;
	}
	
	public void doInteractionsFrom(Mario m) {

		for (Goomba goomba : goombas ) {
			if(m.interactWith(goomba)) break;
		}
	}
	
	public boolean isGoombaPosition(Position p) {
		for (Goomba goomba : goombas ) {
			if (goomba.isInPosition(p)) return true;
		}
		return false;
	}
	
	private GameObject findMario() {
		for(GameObject obj : gameObjects) {
			if(obj.isAlive() && obj.isMario()) {
				return obj;
			}
		}
		return null;
	}
	
	private GameObject findExitDoor() {
		for(GameObject obj : gameObjects) {
			if(obj.isAlive() && obj.isExitDoor()) {
				return obj;
			}
		}
		return null;
	}
	
	
}
