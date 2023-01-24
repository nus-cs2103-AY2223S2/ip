package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.ToDo;
import twofive.ui.Ui;

/**
 * Adds a new ToDo task given a description when command is executed.
 */
public class ToDoCommand extends Command {
    private String taskDescription;

    public ToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Adds a new ToDo task given a description.
     * If task is added successfully, display success message.
     *
     * @param tasks List of tasks to be added to.
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newToDo = new ToDo(taskDescription);
        //Adds new task to list of tasks
        tasks.addTask(newToDo);
        ui.showMessage("Got it. I've added this task:\n " + newToDo + "\n"
                + "Now you have " + tasks.getTasksNum() + " tasks in the list");
    }
}
