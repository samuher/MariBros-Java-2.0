package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;

public interface Command {
	public void execute(GameModel game, GameView view) throws CommandExecuteException;	  
	public Command parse(String[] commandWords) throws CommandParseException;
	public String helpText();
}
