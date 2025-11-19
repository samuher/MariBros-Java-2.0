package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		//Este es el constructor del padre (AbstractCommand)
		//Cada vez que creamos un comando, le pasmos estos datos
		super(name, shortcut, details, help);
		
		//Entonces el constructor padre los guarda en variables(this.name,...)
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0])) {
			return this;
		}
		return null;
	}
}
