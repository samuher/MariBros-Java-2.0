package tp1.control.commands;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

/*
 * Command
 * 	  |
 * AbstractCommand
 *    |							|
 * ActionCommand				| (Proviene de AbstractCommand)
 * (Gurda y lleva la 			
 * lista de acciones)
 * */
public class ActionCommand extends AbstractCommand{
	private List<Action> actions; // lista de acciones 
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
	private static final String HELP = Messages.COMMAND_ACTION_HELP;
	
	//Constructor
	public ActionCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.actions = new ArrayList<>();
	}
	
	//Metodo parse : Encargada de convertir el texto en acciones
	
	@Override
	public Command parse(String[] words) {
		//Creo que me falta el equals y el toLowerCase ??
		if(words.length >= 2 && 
				(words[0].toLowerCase().equalsIgnoreCase("a") || words[0].toLowerCase().equalsIgnoreCase("action"))) {

			//Procesamos cada accion
			for(int i = 1; i<words.length; i++) {
				switch(words[i].toLowerCase()) {
				case "l", "left" -> this.actions.add(Action.LEFT);
                case "r", "right" -> this.actions.add(Action.RIGHT);
                case "u", "up" -> this.actions.add(Action.UP);
                case "d", "down" -> this.actions.add(Action.DOWN);
                case "s", "stop" -> this.actions.add(Action.STOP);
                default -> {
                	return null;
                	}
				}//switch
				
			}//for
			// return cmd
			return this; //Devuelve el comando (el mismo) con las acciones cargadas
		}
		return null; // No es el comando esperado -> action
	}
	
	//Metodo encargado de agragar esas acciones a la lista de acciones de Mario 
	//y Actualiza el juego
	@Override
	public void execute(GameModel game, GameView view) {
		//Añadir todas la sacciones a la lista de acciones de Mario
		//Copiar private actions a mario actionlist 
		while(!actions.isEmpty()) {
			Action action = this.actions.remove(0); // ejecuta accion x, x es la siguiente accion que le toca ejecutar
			if(action != null) {
				game.addAction(action); //Agragamos la accion al juego
			}
		}
		game.update(); // Actualiza el estado del jeugo con las acciones
		view.showGame();
	}
	
}
