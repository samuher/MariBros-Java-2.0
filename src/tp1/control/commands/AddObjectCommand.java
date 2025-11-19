package tp1.control.commands;

import java.util.Arrays;
import tp1.logic.GameModel;
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
		// game - game llama a gamefactory y añade el objeto
		//System.out.println("exe");
		if(!game.parseGameObjectFactory(objWords)) {
			view.showError(Messages.INVALID_GAME_OBJECT.formatted(String.join(" ", objWords)));
		}else {
			view.showGame();
		}
	}

	@Override
	public Command parse(String[] commandWords) {
		
		// comprobamos longitud y ejeccucion del comando deseado
		if (commandWords.length >= 3 && (commandWords[0].toLowerCase().equals(getName()) || commandWords[0].toLowerCase().equals(getShortcut()))) {
			// preparar objWords para eliminar el comando
			// 0 = (x,y), 1 = objeto , 2 = atributo (opc), 3 = atributo (opc)
			this.objWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
			return this;
		}
				
		return null;
	}
	

}
