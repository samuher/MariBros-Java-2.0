package tp1.control;

import java.util.Iterator;


import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		
		boolean help = false;
		
		while (!game.isFinished()) {
		view.showWelcome();
		
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		
		if (!help) {
			view.showGame();
		} 
		
		String[] prompt = view.getPrompt();

		System.out.println(prompt[0]);
		
		// comando action
		
		if (prompt[0].equals("a")|| prompt[0].toLowerCase().equals("action")) {
			System.out.println("Ejecutamos acciones");
			// convierte cada accion en objectos action
			// añadir esos objetos action a una Action List en mario con game.addAction
			//ActionList list = new ActionList();
			for (int i = 1; i < prompt.length; i++) {
				if (prompt[i].equals("l")|| prompt[i].toLowerCase().equals("left")) {
					game.addAction(Action.LEFT);
				} else  if (prompt[i].equals("r")|| prompt[i].toLowerCase().equals("right")) {
					game.addAction(Action.RIGHT);
				} else if (prompt[i].equals("u")|| prompt[i].toLowerCase().equals("up")) {
					game.addAction(Action.UP);
				} else if (prompt[i].equals("d")|| prompt[i].toLowerCase().equals("down")) {
					game.addAction(Action.DOWN);
				} else if (prompt[i].equals("s")|| prompt[i].toLowerCase().equals("stop")) {
					game.addAction(Action.STOP);
				} else {
					System.out.println(tp1.view.Messages.INVALID_COMMAND_PARAMETERS);;
				}
			}
		}
		
		
		
		if (prompt[0].equals("r1")){
			System.out.println("es r");
			System.out.println("entra");
				if (prompt[0].length() > 1 ) {
			System.out.println("Cargando nuevo nivel " + prompt[0].charAt(1));
			//view.setGame(1);
			//view.setGame(new Game(1));
			game.reset(1);
		} else if (prompt[0].equals("r")){
			game.reset();
		}
				
		if (prompt[0].equals("r")) {
			if (prompt.length > 0) {
				System.out.println("Cargando nuevo nivel " + prompt[1]);
				game.reset(Integer.parseInt(prompt[1]));
			}
		}		
				
				
		//System.out.println("tamaño " + prompt.length);
		
		}
		
		switch (prompt[0]) {
		case "h", "help" -> {
			for (String linea : Messages.HELP_LINES) {
				System.out.println(linea);
			}
			help = true;
		}
		case "a", "action" -> System.out.println("accion");
		case "u", "update" -> {game.update();}
		case "e", "exit" -> {game.finish();}
		default -> System.out.println("Comando no recononcido");
		}
		
		
		}
		view.showEndMessage();
	}

}
