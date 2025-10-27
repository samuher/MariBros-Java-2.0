package tp1.control.commands;

import tp1.logic.ActionList;

import java.util.ArrayList;
import java.util.List;

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
	private List<Action> actions; // lista de acciones 
	
	//Constructor
	public ActionCommand() {
		super("action", "a", "[a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+", "user performs actions");
		this.actions = new ArrayList<>();
	}
	
	//Metodo parse : Encargada de convertir el texto en acciones
	
	@Override
	public Command parse(String[] words) {
		//Creo que me falta el equals y el toLowerCase ??
		if(words.length >= 2 && 
				(words[0].equalsIgnoreCase("a") || words[0].equalsIgnoreCase("action"))) {

			//Procesamos cada accion
			for(int i = 1; i<words.length; i++) {
				switch(words[i].toLowerCase()) {
				case "l", "left" -> this.actions.add(Action.LEFT);
                case "r", "right" -> this.actions.add(Action.RIGHT);
                case "u", "up" -> this.actions.add(Action.UP);
                case "d", "down" -> this.actions.add(Action.DOWN);
                case "s", "stop" -> this.actions.add(Action.STOP);
                default -> {
                	// Comando inválido
                	//System.out.printf(Messages.ERROR + "%n", String.format(Messages.UNKNOWN_ACTION, words[i]));
                	//return null;
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
	public void execute(Game game, GameView view) {
		//Añadir todas la sacciones a la lista de acciones de Mario
		//Copiar private actions a mario actionlist 
		while(!actions.isEmpty()) {
			Action action = this.actions.remove(0); // ejecuta accion x, x es la siguiente accion que le toca ejecutar
			if(action != null) {
				game.addAction(action); //Agragamos la accion al juego
			}
		}
		game.restringirLista(); // metodo para cambiar 
		game.update(); // Actualiza el estado del jeugo con las acciones
		view.showGame();
	}
	
}
