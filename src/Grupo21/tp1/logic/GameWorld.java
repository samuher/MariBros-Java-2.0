package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Mario;

public interface GameWorld {
	public boolean isSolid(Position pos);
	public void addPoints(int points);
	public void marioExited();
	public void marioDead();
	public void clean();
	public boolean isMarioWins();
	public boolean interactWith();
	
	public void doInteraction(GameObject gobj);
	public void addMushroom(Position pos);
	public void replaceMario(Mario m);
	
}
