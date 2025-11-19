package tp1.logic;



import tp1.logic.gameobjects.*;


public class Game implements GameWorld, GameModel, GameStatus{

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int remainingTime;
	private int points;
	private int lives;
	
	private int nLevel;
	
	private Mario mario;
	
	private GameObjectContainer gameObjects;
	
	private boolean finish = false;
	private boolean wins = false;
	private boolean looses = false;
	
	public Game(int nLevel) {
		this.remainingTime = 100;
		this.points = 0;
		this.lives = 3;
		this.nLevel = nLevel;
		reset(nLevel);
	}
	
	public boolean parseGameObjectFactory(String objWords[]){
		GameObject gameobject = GameObjectFactory.parse(objWords, this);
		if (gameobject != null) {
			//gameObjects.addObjectFactory(gameobject);
			gameobject.add(gameObjects);
			return true;
		}else{
			return false;
		}
	}
	
	public void marioExited() {
		this.wins = true;
		//tick();
		this.points += this.remainingTime *10;
		this.remainingTime = 0;
		finish();
	}
	
	public boolean isFinished() {
		return finish;
	}
	
	public void finish() {
		this.finish = true;
	}
	
	public boolean reset(int nLevel) {
		this.nLevel = nLevel;
		switch (nLevel) {
		case 0 -> initLevel0();
		case 1 -> initLevel1();
		case 2 -> initLevel2();
		case -1 -> initLevelnegative1();
		default ->{
			return false;
		}
		}
		return true;
	}
	
	public void reset() {
		reset(this.nLevel);
	}
	
	public void resetStats() {
		this.points = 0;
		this.lives = 3;
	}
	
	public String positionToString(int col, int row) {
		
		return this.gameObjects.positionToString(new Position(col, row));
	}

	public void update() {
		tick();
		if (finish) {
			
		}
		gameObjects.update();
	}
	
	public boolean isSolid(Position pos) {
		return gameObjects.isSolid(pos);
	}
	
	public void addAction(Action act) {
		gameObjects.addAction(act);
	}
	
	public boolean playerWins() {
		
		return (this.finish && this.wins);
	}

	public int remainingTime() {
		
		return this.remainingTime;
	}

	public int points() {
		
		return this.points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}

	public int numLives() {
		
		return this.lives;
	}

	@Override
	public String toString() {
		
		return "TODO: Hola soy el game";
	}

	public boolean playerLoses() {
		
		return this.looses;
	}
	
	public void marioDead() {
		lives--;
		if (this.lives == 0) {
			//finish();
			//System.out.println(Messages.GAME_OVER);
			this.looses = true;
			finish();
		}else {
			reset();
		}
	}
	
	
	public void doInteraction(GameObject gobj) {
		gameObjects.doInteraction(gobj);
	}
	
	
	private void initLevel0() {
		//this.nLevel = 0;
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
		int tamX = 8;
		//tamY= 8;
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
		//gameObjects.add(new Goomba(this, new Position(12, 4)));
	}
	
	private void initLevel1() {
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
		int tamX = 8;
		//tamY= 8;
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
		gameObjects.add(new Goomba(this, new Position(4,6)));
		gameObjects.add(new Goomba(this, new Position(12,6)));
		gameObjects.add(new Goomba(this, new Position(12,8)));
		gameObjects.add(new Goomba(this, new Position(10,10)));
		gameObjects.add(new Goomba(this, new Position(12,11)));
		gameObjects.add(new Goomba(this, new Position(12,14)));
	}
	
	private void initLevel2() {
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
		int tamX = 8;
		//tamY= 8;
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
		gameObjects.add(new Goomba(this, new Position(4,6)));
		gameObjects.add(new Goomba(this, new Position(12,6)));
		gameObjects.add(new Goomba(this, new Position(12,8)));
		gameObjects.add(new Goomba(this, new Position(10,10)));
		gameObjects.add(new Goomba(this, new Position(12,11)));
		gameObjects.add(new Goomba(this, new Position(12,14)));
		
		//practica 2_2
		// Para probar estas estensiones crear un nuevo nivel 2 que sea exactamente igual el nivel 1 
		// pero con una caja en la posición (9,4) y dos setas en las posiciones (12,8) y (2,20). 
		//Las posiciones indicadas siguen el formato: (fila,columna).
		gameObjects.add(new Box(this, new Position(9,4)));
		gameObjects.add(new MushRoom(this, new Position(12,8)));
		gameObjects.add(new MushRoom(this, new Position(2,20)));
	}
	
	private void initLevelnegative1(){
		this.remainingTime = 100;
		gameObjects = new GameObjectContainer();
	}

	
	public void tick() {
		remainingTime--;
	}
	
	public boolean isMarioWins() {
		return (this.finish && this.wins);
	}
	

	@Override
	public void clean() {
		
		
	}

	@Override
	public boolean interactWith() {
		
		return false;
	}
	
	public void exit() {
		finish();
	}
	
	
	// añadir seta desde box
	public void addMushroom(Position p) {
		gameObjects.addPending(new MushRoom(this, p.moved(Action.UP)));
	}

	@Override
	public void replaceMario(Mario m) {
		
		this.mario = m;
	}
	
}
