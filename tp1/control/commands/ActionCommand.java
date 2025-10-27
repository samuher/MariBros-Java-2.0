package tp1.control.commands;

import tp1.logic.ActionList;
import tp1.logic.Action;
import tp1.logic.Game;
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
	private ActionList actions; // lista de acciones 
	
	//Constructor
	public ActionCommand() {
		super("action", "a", "[a]ction <actions>", "performs one or more actions");
	}
	
	//Metodo parse : Encargada de convertir el texto en acciones
	
	@Override
	public Command parse(String[] words) {
		//Creo que me falta el equals y el toLowerCase ??
		if(words.length >= 2 && 
				(words[0].equalsIgnoreCase("a") || words[0].equalsIgnoreCase("action"))) {
			ActionCommand cmd = new ActionCommand();
			cmd.actions = new ActionList();
			
			//Procesamos cada accion
			for(int i = 1; i<words.length; i++) {
				switch(words[i].toLowerCase()) {
				case "l", "left" -> cmd.actions.add(Action.LEFT);
                case "r", "right" -> cmd.actions.add(Action.RIGHT);
                case "u", "up" -> cmd.actions.add(Action.UP);
                case "d", "down" -> cmd.actions.add(Action.DOWN);
                case "s", "stop" -> cmd.actions.add(Action.STOP);
                default -> {
                	// Comando inválido
                	System.out.printf(Messages.ERROR + "%n", String.format(Messages.UNKNOWN_ACTION, words[i]));
                	return null;
                	}
				}//switch
				
			}//for
			cmd.actions.restringirLista();
			return cmd; //Devuelve el nuevo comando con las acciones cargadas
		}
		return null; // No es el comando esperado -> action
	}
	
	//Metodo encargado de agragar esas acciones a la lista de acciones de Mario 
	//y Actualiza el juego
	@Override
	public void execute(Game game, GameView view) {
		//Añadir todas la sacciones a la lista de acciones de Mario
		while(actions.anyActions()) {
			Action action = actions.nextAction();
			if(action != null) {
				game.addAction(action); //Agragamos la accion al juego
			}
		}
		game.update(); // Actualiza el estado del jeugo con las acciones
		
		view.showGame();
	}
	
}
