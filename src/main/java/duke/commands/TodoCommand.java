package duke.commands;

import duke.exceptions.DukeInvalidTodoCommandException;
import duke.tasks.TodoTask;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import java.util.Arrays;

public class TodoCommand extends Command {

    private final String[] tokens;

    public TodoCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidTodoCommandException {

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, tokens.length);

        if (taskNameArray.length == 0) {
            throw new DukeInvalidTodoCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        TodoTask newTodoTask = new TodoTask(taskName);

        taskList.addTask(newTodoTask);
        ui.showMessage("Added:\n" + newTodoTask);
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
