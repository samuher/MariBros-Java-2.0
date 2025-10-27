package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView;

public class UpdateCommand extends NoParamsCommand {

	//Constructor
	public UpdateCommand() {
        super("update", "u", "[u]pdate", "updates the game state");
    }

    @Override
    public void execute(Game game, GameView view) {
        game.update();
    }
}
