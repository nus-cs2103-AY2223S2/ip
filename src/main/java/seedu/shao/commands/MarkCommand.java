package seedu.shao.commands;

import seedu.shao.storage.Storage;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

public class MarkCommand extends Command {

	private boolean isMark;

	public MarkCommand(String[] messageArray, boolean isMark) {
		super(messageArray);
		this.isMark = isMark;
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Storage storage = (Storage) args[2];
		TaskList tasklist = (TaskList) args[3];
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			ui.printError("Oops! The item number cannot be empty.");
			return;
		}
		try {
			tasklist.markItem(inputArr[1], isMark, storage, ui);
		} catch (NumberFormatException ex) {
			ui.printError("Oops! An item number must be provided.");
		}
	}

}