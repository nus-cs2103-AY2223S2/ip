package seedu.shao.commands;

import seedu.shao.parser.Parser;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

public class DateTimeCommand extends Command {

	public DateTimeCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Parser parser = (Parser) args[1];
		TaskList tasklist = (TaskList) args[3];
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			ui.printError("Oops! Datetime cannot be empty.");
			return;
		}
		ui.printDeadlineEventOnDatetime(tasklist,
				parser.sliceArrAndConcate(inputArr, 1, inputArr.length), parser);
	}

}