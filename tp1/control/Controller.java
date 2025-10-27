package tp1.control;


import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */

//Menejara objetos de tipo Command
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
	    view.showWelcome();
	    
	    while (!game.isFinished()) {
	        // Muestra el estado actual del juego
	        view.showGame();
	        
	        String[] prompt = view.getPrompt();
	        Command command = CommandGenerator.parse(prompt);
	        
	        if (command != null) {
	            command.execute(game, view);
	        } else {
	            System.out.printf(Messages.ERROR + "%n", String.format(Messages.UNKNOWN_COMMAND, prompt[0]));
	        }
	    }
	    
	    // Muestra el estado final del juego
	    view.showGame();
	    view.showEndMessage();
		
	}//run
	
}//class
