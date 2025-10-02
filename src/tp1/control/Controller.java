package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;

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
		
		while (!game.isFinished()) {
		view.showWelcome();
		
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		view.showGame();
		
		String[] prompt = view.getPrompt();

		System.out.println(prompt[0]);
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
		//System.out.println("tamaño " + prompt.length);
		
		}
		
		switch (prompt[0]) {
		case "h", "help" -> System.out.println("Available commands:\r\n"
				+ "   [a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+: user performs actions\r\n"
				+ "   [u]pdate | \"\": user does not perform any action\r\n"
				+ "   [r]eset [numLevel]: reset the game to initial configuration if not numLevel else load the numLevel map\r\n"
				+ "   [h]elp: print this help message\r\n"
				+ "   [e]xit: exits the game");
		case "a", "action" -> System.out.println("accion");
		case "u", "update" -> {}
		case "e", "exit" -> {game.finish();}
		default -> System.out.println("Comando no recononcido");
		}
		
		
		}
		view.showEndMessage();
	}

}
