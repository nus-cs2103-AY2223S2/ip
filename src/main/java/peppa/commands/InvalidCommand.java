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
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        return "Boink! Peppa couldn't understand that. Please use one of the commands below:\n"
                + Ui.getCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
