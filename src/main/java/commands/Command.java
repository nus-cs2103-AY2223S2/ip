package commands;

import ui.Ui;
import storage.Storage;

import javafx.scene.layout.VBox;

public class Command {
	private String[] messageArray;

	public Command(String[] messageArray) {
		this.messageArray = messageArray;
	}

	public String[] getMessageArray() {
		return this.messageArray;
	}

	/**
	 * Exit the program if true.
	 * 
	 * @return boolean
	 */
	public boolean isExit() {
		return false;
	};

	/**
	 * Execute the command and print a response.
	 * 
	 * @param args
	 */
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Storage storage = (Storage) args[2];
		VBox dialogContainer = (VBox) args[4];
		ui.sendResponse(dialogContainer, storage,
				ui.createLabel("Oops! I'm sorry but I don't know what that means."));
	};
}
