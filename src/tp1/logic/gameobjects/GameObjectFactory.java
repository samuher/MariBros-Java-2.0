package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.logic.GameWorld;

public class GameObjectFactory {

	public GameObjectFactory() {
	}
	private static final List<GameObject> availableObjects  = Arrays.asList(
			new Land(),
			new ExitDoor(),
			new Goomba(),
			new Mario(),
			new Box(),
			new MushRoom()
	);
	public static GameObject parse (String objWords[], GameWorld game) {
		for (GameObject c: availableObjects) {
			GameObject parsed = c.parse(objWords, game);
			if(parsed != null) return parsed;
		}
		return null;
		
	}
}
