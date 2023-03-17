package commands;

import ui.Ui;

import storage.Storage;

import parser.Parser;

import tasklist.TaskList;

import javafx.scene.layout.VBox;

public class DateTimeCommand extends Command {

	public DateTimeCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Parser parser = (Parser) args[1];
		Storage storage = (Storage) args[2];
		TaskList tasklist = (TaskList) args[3];
		VBox dialogContainer = (VBox) args[4];
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			ui.sendResponse(dialogContainer, storage,
					ui.createLabel("Oops! Datetime cannot be empty."));
			return;
		}
		ui.printDeadlineEventOnDatetime(tasklist, storage, parser, dialogContainer,
				parser.sliceArrAndConcate(inputArr, 1, inputArr.length));
	}

}