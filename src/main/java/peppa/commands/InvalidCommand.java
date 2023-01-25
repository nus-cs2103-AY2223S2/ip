package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents an invalid command that the chatbot fails to recognise.
 */
public class InvalidCommand implements Command {
    public InvalidCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        Ui.displayMessage("Boink! Peppa couldn't understand that. " +
                "Please use one of the commands below:");
        Ui.displayCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
