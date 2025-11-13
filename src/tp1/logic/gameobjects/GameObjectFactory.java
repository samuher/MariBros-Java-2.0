package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.control.commands.ActionCommand;
import tp1.control.commands.Command;
import tp1.control.commands.ExitCommand;
import tp1.control.commands.HelpCommand;
import tp1.control.commands.ResetCommand;
import tp1.control.commands.UpdateCommand;
import tp1.logic.GameWorld;
import tp1.logic.Land;

public class GameObjectFactory {

	public GameObjectFactory() {
		// TODO Auto-generated constructor stub
	}
	private static final List<GameObject> availableObjects  = Arrays.asList(
			//TODO fill with your code
			new Land(),
			new ExitDoor(),
			new Goomba(),
			new Mario()
	);
	public static GameObject parse (String objWords[], GameWorld game) {
		for (GameObject c: availableObjects) {
			//TODO fill with your code
			GameObject parsed = c.parse(objWords, game);
			if(parsed != null) return parsed;
		}
		return null;
		
	}
}
