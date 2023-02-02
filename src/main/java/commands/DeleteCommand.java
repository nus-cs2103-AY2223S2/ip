package commands;

import ui.Ui;

import storage.Storage;

import javafx.scene.layout.VBox;

import tasklist.TaskList;

public class DeleteCommand extends Command {

	public DeleteCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Storage storage = (Storage) args[2];
		TaskList tasklist = (TaskList) args[3];
		VBox dialogContainer = (VBox) args[4];
		String[] inputArr = getMessageArray();
		String errorMessage;
		if (inputArr.length < 2) {
			errorMessage = "Oops! The item number cannot be empty.";
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
			return;
		}
		try {
			tasklist.deleteTask(ui, storage, dialogContainer, inputArr[1]);
		} catch (NumberFormatException ex) {
			errorMessage = "Oops! An item number must be provided.";
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		}
	}

}