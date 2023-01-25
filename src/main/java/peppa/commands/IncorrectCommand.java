package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents an incorrect command that the chatbot recognises, but failed to parse correctly.
 * Could be a result of wrong parameters, incorrect formatting, etc.
 */
public class IncorrectCommand implements Command {
    public IncorrectCommand() {}

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
