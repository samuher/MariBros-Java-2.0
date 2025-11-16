package tp1.control.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand{

	//private List<GameObject> actions; // lista de acciones 
	private static final String NAME = Messages.COMMAND_ADDOBJECT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ADDOBJECT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ADDOBJECT_DETAILS;
	private static final String HELP = Messages.COMMAND_ADDOBJECT_HELP;
	
	// prompt como lista de strings con el objeto con sus propiedades
	private String[] objWords;
	
	//Constructor
	public AddObjectCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		//this.objWords = new ArrayList<String>();
	}

	@Override
	public void execute(GameModel game, GameView view) {
		// TODO Auto-generated method stub
		// game - game llama a gamefactory y añade el objeto
		//System.out.println("exe");
		if(!game.parseGameObjectFactory(objWords)) {
			view.showError(Messages.INVALID_GAME_OBJECT.formatted(objWords[2]));
		}else {
			view.showGame();
		}
		
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		// aO (1,2) m
		//System.out.println(commandWords[0].toLowerCase());
		// comprobamos longitud y ejeccucion del comando deseado
		if (commandWords.length >= 3 && (commandWords[0].toLowerCase().equals(getName()) || commandWords[0].toLowerCase().equals(getShortcut()))) {
			// array commandWords
			// 0 = a0, 1 = pos, 2 = objeto , 3 = atributo, 4 = atributo(mario)
			// convertir a 
			// 0 = x , 1 = y, 2 = objeto, 3 = 1 atributo , 4 = atributo opcional
			//System.out.println("entra en aO" + commandWords.length);
			// sacar datos de pos
			String vpos; 
			//String[] objwordscopy = Arrays.copyOfRange(commandWords, 0, 1); // objwords copy, pvalor (0,0) 
			// 1. Procesar las dos primeras palabras (entre paréntesis)
			String[] objwordscopy = commandWords[1].split(",");
			objwordscopy[0] = objwordscopy[0].replaceAll("\\(", "");
			objwordscopy[1] = objwordscopy[1].replaceAll("\\)", "");

			// 2. Calcular tamaño del array final
			int size = 3; // siempre habrá: obj1, obj2, nObjeto
			if (commandWords.length > 3) size++;   // atributo1
			if (commandWords.length > 4) size++;   // atributo2 (mario, etc.)

			// 3. Crear array del tamaño exacto
			String[] objWords = new String[size];

			// 4. Rellenar el array
			int i = 0;
			objWords[i++] = objwordscopy[0];
			objWords[i++] = objwordscopy[1];
			objWords[i++] = commandWords[2].toLowerCase();

			if (commandWords.length > 3) {
			    objWords[i++] = commandWords[3].toLowerCase();
			}

			if (commandWords.length > 4) {
			    objWords[i++] = commandWords[4].toLowerCase();
			}
			// añadir lista objWords al objeto
			this.objWords = objWords;
			return this;
		}
		
		//GameObject gameobject = GameObjectFactory.parse(String commandWords[], GameWorld game);
		
		return null;
	}
	

}
