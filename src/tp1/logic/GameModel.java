package tp1.logic;

//Para el control del juego
public interface GameModel {
	public boolean isFinished();
	public void update();
	public void reset();
	public void addAction(Action act);
	public void restringirLista();
	public boolean reset(int Level);
	public void exit();
	public void parseGameObjectFactory(String objWords[]);
}
