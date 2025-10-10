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
	
	private boolean finish = false;

	//TODO fill your code
	
	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		this.remainingTime = 100;
		this.points = 0;
		this.lives = 3;
		System.out.println("creando game");
		this.nLevel = nLevel;
		switch (nLevel) {
		case 0 -> initLevel0();
		case 1 -> {initLevel1(); System.out.println("init level1w");}
		}
	}
	
	public boolean isFinished() {
		return finish;
	}
	
	public void finish() {
		this.finish = true;
	}
	
	
	public void reset(int nLevel) {
		this.remainingTime = 100;
		this.points = 0;
		this.lives = 3;
		switch (nLevel) {
		case 0 -> initLevel0();
		case 1 -> initLevel1();
		}
	}
	
	public void reset() {
		reset(this.nLevel);
	}
	
	
	public String positionToString(int col, int row) {
		// TODO Auto-generated method stub
		return this.gameObjects.positionToString(new Position(col, row));
		
	}

	public void update() {
		remainingTime--;
		gameObjects.update();
	}
	
	public boolean isSolid(Position pos) {
		return gameObjects.isSolid(pos);
	}
	
	public void addAction(Action act) {
		mario.addAction(act);
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
	
	public void addPoints(int points) {
		this.points += points;
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
	
	public void marioDead() {
		lives--;
		if (this.lives == 0) {
			reset();
			System.out.println(Messages.GAME_OVER);
		}
	}
	
	public void doInteractionsFrom(Mario m) {
		// game ob -> goombas -> posicion = posicion mario
		gameObjects.doInteractionsFrom(m);
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
	
	private void initLevel1() {
		System.out.println("level 1");
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
	}

	
}
