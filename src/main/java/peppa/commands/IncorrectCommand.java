package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents an incorrect command that the chatbot recognises, but failed to parse correctly.
 * Could be a result of wrong parameters, incorrect formatting, etc.
 */
public class IncorrectCommand implements Command {
    private String errorMessage;

    public IncorrectCommand() {}

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        return errorMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
