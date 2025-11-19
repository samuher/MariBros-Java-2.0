package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class UpdateCommand extends NoParamsCommand {
	private static final String NAME = Messages.COMMAND_UPDATE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_UPDATE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_UPDATE_DETAILS;
	private static final String HELP = Messages.COMMAND_UPDATE_HELP;
	//Constructor
	public UpdateCommand() {
        super(NAME,SHORTCUT,DETAILS,HELP);
    }

    @Override
    public void execute(GameModel game, GameView view) {
        game.update();
        view.showGame();
    }
    
    public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && (matchCommandName(commandWords[0])|| commandWords[0].equals(""))) {
			return this;
		}
		return null;
	}
}
