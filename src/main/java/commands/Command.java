package commands;

import ui.Ui;

public class Command {
	private String[] messageArray;

	public Command(String[] messageArray) {
		this.messageArray = messageArray;
	}

	public String[] getMessageArray() {
		return this.messageArray;
	}

	public boolean isExit() {
		return false;
	};

	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		ui.printError("Oops! I'm sorry but I don't know what that means.");
	};
}
