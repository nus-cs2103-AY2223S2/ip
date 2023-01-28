package commands;

import javafx.scene.layout.VBox;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

	public ListCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Storage storage = (Storage) args[2];
		TaskList tasklist = (TaskList) args[3];
		VBox dialogContainer = (VBox) args[4];
		ui.printList(tasklist, storage, dialogContainer);
	}

}