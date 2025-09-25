package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int remainingTime;
	private int points;
	private int lives;
	
	private int nLevel;
	
	private Mario mario;
	
	private GameObjectContainer gameObjects;

	//TODO fill your code
	
	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		this.remainingTime = 100;
		this.points = 0;
		this.lives = 3;
		if (nLevel == 0) {
			initLevel0();
		}
	}
	
	public String positionToString(int col, int row) {
		// TODO Auto-generated method stub
		System.out.println(""+col+" "+row);
		
		if (this.gameObjects.getMario().getPosition().getCol() == col && this.gameObjects.getMario().getPosition().getRow() == row) {
			return this.gameObjects.getMario().getIcon();
		}
		
		if (this.gameObjects.getExitDoor().getPosition().getCol() == col && this.gameObjects.getMario().getPosition().getRow() == row) {
			return this.gameObjects.getExitDoor().getIcon();
		}
		
		if (this.gameObjects.getLand()[col][row] != null){
			return this.gameObjects.getLand()[col][row].getIcon();
		}
		
		ArrayList<Goomba> goombas = gameObjects.getGoombas();
		// leer posiciones goomba
		System.out.println(goombas.size());
		
		for (int i = 0; i < goombas.size(); i++) {
			System.out.println("Goomba "+ i);
			if(goombas.get(i).getPos().getCol() == col && goombas.get(i).getPos().getRow() == row) {
				System.out.println("hay goomba");
				return goombas.get(i).getIcon();
			}
		}
		
		
		return Messages.EMPTY;
		
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return this.remainingTime;
	}

	public int points() {
		// TODO Auto-generated method stub
		return this.points;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return this.lives;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}

	public boolean playerLoses() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(new Position(13,col)));
			gameObjects.add(new Land(new Position(14,col)));		
		}

		gameObjects.add(new Land(new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(new Position(9,2)));
		gameObjects.add(new Land(new Position(9,5)));
		gameObjects.add(new Land(new Position(9,6)));
		gameObjects.add(new Land(new Position(9,7)));
		gameObjects.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);

		gameObjects.add(new Goomba(this, new Position(0, 19)));
		gameObjects.add(new Goomba(this, new Position(12, 4)));
	}
	
}
