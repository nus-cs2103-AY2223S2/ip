package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final String INDEX_STRING;

    public DeleteCommand(String commandString, String indexString) {
        super(AvailableCommands.DELETE, commandString);
        INDEX_STRING = indexString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        int index = isValidIndex(INDEX_STRING, tasks);

        Task task = tasks.get(index);
        tasks.remove(index);

        ui.showDeleteTask(task.toString(), tasks.size());
    }
}
