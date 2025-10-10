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
	private int contador;
	
	public GameObjectContainer(){
		landList = new ArrayList<>();
		goombas = new ArrayList<>();
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
	
	public void update() {
		//llamar a los metodos update de los objetos del tableroa
		// primero se actualiza mario y luego los goombas
		this.mario.update();
		this.mario.interactWith(exit);
		//this.goombas.removeIf(Goomba :: isDead);
		System.out.println("update");
		for (Goomba goomba : goombas ) {
			goomba.update();
		}
		doInteractionsFrom(mario);
		clean();
	}
	
	public void clean() {
		for (Goomba goomba : goombas ) {
			if (goomba.isDead()) {
				goombas.remove(goomba);
				return;
			}
		}
	}
	
	public String positionToString(Position pos) {
		//int col = pos.getCol();
		//int row = pos.getRow();
		for (Land land : landList) {
			if (land.isInPosition(pos)) {
				return land.getIcon();			}
		}
		
		for (Goomba goomba : goombas ) {
			if (goomba.isInPosition(pos) && !goomba.isDead()) {
				return goomba.getIcon();
			}
			if (goomba.isDead()) {
				//System.out.println("No se imprime goomba esta muerto.");
			}
		}
		
		if (mario.isInPosition(pos)) {
			return mario.getIcon();
		}
		
		if (exit.isInPosition(pos)) {
			return exit.getIcon();
		}
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
			m.interactWith(goomba);
		}
	}
}
