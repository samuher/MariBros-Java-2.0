package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
	
	private Integer level; //Entero, sin decimales
	
	//Constructor
	public ResetCommand() {
        super("reset", "r", "[r]eset [numLevel]", "reset the game to initial configuration if not numLevel else load the numLevel map");
    }

    @Override
    public void execute(Game game, GameView view) {
    	if(level == null) {
    		game.reset();
    		view.showGame();
    	}else {
    		if(!game.reset(level)) {
    			view.showError(Messages.INVALID_LEVEL_NUMBER);
    		}else {
    			view.showGame();
    		}
    	}
    	
    }
    
    @Override
    public Command parse(String[] words) {
    	if(words.length >= 1 && 
    			(words[0].equalsIgnoreCase(getName()) || 
    					(words[0].equalsIgnoreCase(getShortcut())))) {
    		
    		ResetCommand cmd = new ResetCommand();
    		
    		if(words.length > 1) {
    			for (int i = 0; i < words[1].length(); i++) {
    		        if (!Character.isDigit(words[1].charAt(i))) {
    		            //return false;
    		        	// not a valid number
    		        	return null;
    		        }
    		    }
    			
    			cmd.level = Integer.parseInt(words[1]);
    		}
    		return cmd;
    	}
    	
    	return null;
    }
}
