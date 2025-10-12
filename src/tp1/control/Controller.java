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
		view.showWelcome();
		while (!game.isFinished()) {
		
		
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		
		if (!help) {
			view.showGame();
			game.tick();
		} else{
			help = false;
		}
		
		
		
		String[] prompt = view.getPrompt();

		//System.out.println(prompt[0]);
		
		// comando action
		
		if (prompt[0].equals("a")|| prompt[0].toLowerCase().equals("action")) {
			
			if (prompt.length > 1) {
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
						//System.out.printf(tp1.view.Messages.UNKNOWN_ACTION + "\n", prompt[i]);
						System.out.printf(Messages.ERROR + "%n", String.format(Messages.UNKNOWN_ACTION, prompt[i]));
						game.update();
						break;
					}
				}//for
			}else {
				System.out.printf(Messages.ERROR + "\n",Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				help = true;
				//game.update();
			}
			
			
		} else {
			if (prompt[0].equals("r") || prompt[0].toLowerCase().equals("reset")) {
				game.reset();
				if (prompt.length > 1) {
					//System.out.println("Cargando nuevo nivel " + prompt[1]);
					game.reset(Integer.parseInt(prompt[1]));
				}
			} else {
				switch (prompt[0].toLowerCase()) {
				case "h", "help" -> {
					if (prompt.length > 1) {
						System.out.printf(Messages.ERROR + "\n",Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
						help = true;
						//return;
					} else {
						System.out.println(Messages.HELP);
					
					help = true;
					}
					
					
				}
				case "u", "update" -> {
					game.update();
				}
				case "e", "exit" -> {game.finish();}
				case "" -> {
					game.update();
					}
				default -> {System.out.printf(Messages.ERROR + "%n", String.format(Messages.UNKNOWN_COMMAND, prompt[0]));
							help = true;}
				
				}//switch
			}//else
			
			
		}
		
		
		
	}view.showEndMessage(); }//while
	}//class
