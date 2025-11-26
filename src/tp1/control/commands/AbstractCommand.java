package tp1.control.commands;


import tp1.view.Messages;

/*
 * Command
 * 	  |
 * AbstractCommand
 * */
public abstract class AbstractCommand implements Command {

	// Forman parte de atributos de estado
	private final String name;
	private final String shortcut;
	private final String details;
	private final String help;
	
	//super()
	public AbstractCommand(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || 
			   this.name.equalsIgnoreCase(name);
	}

	@Override
	public String helpText(){
		return Messages.LINE_TAB.formatted(Messages.COMMAND_HELP_TEXT.formatted(this.details, this.help));
	}
}
