package commands;

import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

	public ListCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		TaskList tasklist = (TaskList) args[3];
		ui.printList(tasklist);
	}

}