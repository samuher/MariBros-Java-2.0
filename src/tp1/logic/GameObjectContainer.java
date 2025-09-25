package tp1.logic;

import tp1.logic.gameobjects.*;

import java.util.ArrayList;

public class GameObjectContainer {
	//TODO fill your code
	private Land[][] land;
	private Mario mario;
	private ExitDoor exit;
	ArrayList<Goomba> goombas = new ArrayList<>();
	private int contador;
	
	public GameObjectContainer(){
		land = new Land[Game.DIM_X][Game.DIM_Y];
	}
	
	public void add(Land land) {
		this.land[land.getPosition().getCol()][land.getPosition().getRow()]= land;
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
	
	public Land[][] getLand(){
		return this.land;
	}
	
	public ArrayList<Goomba> getGoombas(){
		return this.goombas;
	}
	
	public Mario getMario() {
		return this.mario;
	}
	
	public ExitDoor getExitDoor() {
		return this.exit;
	}
	
}
