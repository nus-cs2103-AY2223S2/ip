package commands;

import javafx.scene.layout.VBox;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command {

	public ExitCommand(String[] messageArray) {
		super(messageArray);
	}

	@Override
	public boolean isExit() {
		return true;
	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Storage storage = (Storage) args[2];
		VBox dialogContainer = (VBox) args[4];
		ui.sendResponse(dialogContainer, storage,
				ui.createLabel("Bye! Have a nice day!"));
	}

}
