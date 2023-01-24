package commands;

import parser.Parser;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand extends Command {

	public FindCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Parser parser = (Parser) args[1];
		TaskList tasklist = (TaskList) args[3];
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			ui.printError("Oops! Search keyword cannot be empty.");
			return;
		}
		ui.printMatchedTasks(tasklist,
				parser.sliceArrAndConcate(inputArr, 1, inputArr.length));
	}

}