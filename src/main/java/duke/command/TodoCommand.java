package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.UI.UI;

/**
 * The todo command.
 * Extends from AddCommand.
 * Handles the adding of a todo task.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";
    private String[] index;

    /**
     * The constructor for the todo command.
     * @param index The arguments of the command.
     */
    public TodoCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        String taskDescription = index[1];
        Task taskToAdd = new Todo(taskDescription);
        taskList.addTask(taskToAdd, storage);
        ui.printResponse("Understood. I have added this task: \n" + taskToAdd);
        ui.printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
