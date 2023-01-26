package commands;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import storage.Storage;
import ui.Ui;

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
		Label message = new Label("Oops! I'm sorry but I don't know what that means.");
		message.setWrapText(true);
		ui.sendResponse(dialogContainer, storage, message);
	};
}
