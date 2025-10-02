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
		//this.goombas.removeIf(Goomba :: isDead);
		for (Goomba goomba : goombas ) {
			goomba.update();
		}
	}
	
	public String positionToString(Position pos) {
		int col = pos.getCol();
		int row = pos.getRow();
		for (Land land : landList) {
			if (land.getCol() == col && land.getRow() == row) {
				return land.getIcon();			}
		}
		
		for (Goomba goomba : goombas ) {
			if (goomba.getCol() == col && goomba.getRow() == row) {
				return goomba.getIcon();
			}
		}
		
		if (mario.getCol() == col && mario.getRow() == row) {
			return mario.getIcon();
		}
		
		if (exit.getCol() == col && exit.getRow() == row) {
			return exit.getIcon();
		}
		return Messages.EMPTY;
	}
	
	public boolean isSolid(Position pos) {
		int col = pos.getCol();
		int row = pos.getRow();
		for (Land land : landList) {
			if (land.getCol() == col && land.getRow() == row) {
				return true;}
		}
		return false;
	}
	
	
}
