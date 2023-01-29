package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Command {
    private final AvailableCommands CURRENT_COMMAND;
    private final String COMMAND_STRING;

    public enum AvailableCommands {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT
    }

    public Command(AvailableCommands currentCommand, String commandString) {
        CURRENT_COMMAND = currentCommand;
        COMMAND_STRING = commandString;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showError(String.format("Error processing %s command", COMMAND_STRING));
    }

    public boolean isExit() {
        return CURRENT_COMMAND == AvailableCommands.EXIT;
    }

    protected int isValidIndex(String indexStr, ArrayList<Task> tasks) throws DukeException {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        boolean isNumber = pattern.matcher(indexStr).matches();

        if (!isNumber) {
            throw new DukeException("Index provided is not an integer.");
        }

        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bounds of tasks list.");
        }

        return index;
    }
}
