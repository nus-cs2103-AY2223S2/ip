package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.task.Todo;
import lele.ui.Ui;

import java.io.IOException;

/**
 * Handless actions taken when user
 * creates a to-do task.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * Instantiates the to-do instance.
     *
     * @param todo
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Adds the to-do task to task list, updates the storage,
     * then printing to the command line the added task.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     * @throws IOException An error with writing to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
            taskList.addTasks(todo);
            storage.updateStorage(taskList);
            ui.printAddTask(taskList, todo);
    }

    /**
     * Adding to-do task does not terminate program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
