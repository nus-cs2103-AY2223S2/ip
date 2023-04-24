package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.ToDo;
import twofive.ui.TaskContainer;

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
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String commandResult = "";
        ToDo newToDo = new ToDo(taskDescription);
        //Adds new task to list of tasks
        tasks.addTask(newToDo);
        try {
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has added this task for you:\n " + newToDo + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list";
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
