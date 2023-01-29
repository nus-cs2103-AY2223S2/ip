package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Command {
    private final Commands COMMAND;
    private final String COMMAND_STRING;

    public enum Commands {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT
    }

    public Command(Commands COMMAND, String COMMAND_STRING) {
        this.COMMAND = COMMAND;
        this.COMMAND_STRING = COMMAND_STRING;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(String.format("Error processing %s command", this.COMMAND_STRING));
    }

    public boolean isExit() {
        return COMMAND == Commands.EXIT;
    }

    protected int isValidIndex(String indexStr, ArrayList<Task> tasks) throws DukeException {
        Pattern p = Pattern.compile("^[0-9]+$");
        boolean isNumber = p.matcher(indexStr).matches();

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
