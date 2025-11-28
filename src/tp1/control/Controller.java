package tp1.control;


import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.exceptions.CommandException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */

//Menejara objetos de tipo Command
public class Controller {

	private GameModel game;
	private GameView view;

	public Controller(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
		}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
	    view.showWelcome();
	 // Muestra el estado actual del juego
        view.showGame();
	    while (!game.isFinished()) {
	        String[] prompt = view.getPrompt();
	        try {
		        Command command = CommandGenerator.parse(prompt);
		        command.execute(game, view);
	        } catch (CommandException e) {
	        	view.showError(e.getMessage());
	 			Throwable cause = e.getCause();
	 			while (cause != null) {
	 				view.showError(cause.getMessage());
	 				cause = cause.getCause();
	 			}
	        }
	            
	    }
	    // Muestra el estado final del juego
	    view.showEndMessage();
			
	}//run
	
}//class
