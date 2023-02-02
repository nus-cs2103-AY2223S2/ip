package commands;

import ui.Ui;
import storage.Storage;
import tasklist.TaskList;

import javafx.scene.layout.VBox;

public class MarkCommand extends Command {

	private boolean isMark;

	public MarkCommand(String[] messageArray, boolean isMark) {
		super(messageArray);
		this.isMark = isMark;
	}

	public boolean getIsMark() {
		return this.isMark;
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
			tasklist.markItem(inputArr[1], isMark, storage, ui, dialogContainer);
		} catch (NumberFormatException ex) {
			errorMessage = "Oops! An item number must be provided.";
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		}
	}

}