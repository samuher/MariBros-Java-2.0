package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
	
	private Integer level; //Entero, sin decimales
	private static final String NAME = Messages.COMMAND_RESET_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
	private static final String HELP = Messages.COMMAND_RESET_HELP;
	
	//Constructor
	public ResetCommand() {
        //super("reset", "r", "[r]eset [numLevel]", "reset the game to initial configuration if not numLevel else load the numLevel map");
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

    @Override
    public void execute(GameModel game, GameView view) {
    	if(level == null) {
    		game.reset();
    		view.showGame();
    	}else {
    		game.resetStats();
    		if(!game.reset(level)) {
    			view.showError(Messages.INVALID_LEVEL_NUMBER);
    		}else {
    			view.showGame();
    		}
    	}
    	
    }
    
    @Override
    public Command parse(String[] words) throws CommandParseException {
    	if(words.length >= 1 && 
    			(words[0].equalsIgnoreCase(this.NAME) || 
    					(words[0].equalsIgnoreCase(this.SHORTCUT)))) {
    		
    		ResetCommand cmd = new ResetCommand();
    		
    		if(words.length > 1) {
    			String arg = words[1];
                
                // comprobar si es -
                int i = 0;
                if (arg.charAt(0) == '-') {
                    // Si es solo un "-", no es un número válido
                    if (arg.length() == 1) return null; 
                    // Empezamos a mirar dígitos a partir del siguiente carácter
                    i = 1; 
                }
                
                // comporbar que no es -(NaN)
//                for (; i < arg.length(); i++) {
//                    char c = arg.charAt(i);
//                    if (!Character.isDigit(c)) {
//                        return null;
//                    }
//                }
                
                // 3. Si llegamos aquí, es seguro convertirlo
                try {
                	 cmd.level = Integer.parseInt(arg);
                } catch(NumberFormatException nfe){
                 	throw new CommandParseException(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(words[1]), nfe);
                }
               
    		}
    		return cmd;
    	}
    	
    	return null;
    }
}
