package commands;

import ui.Ui;

public class ExitCommand extends Command {

	public ExitCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public boolean isExit() {
		return true;
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		ui.println("Bye! Have a nice day!");
	}

}
