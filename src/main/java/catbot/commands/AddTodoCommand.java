package catbot.commands;

import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.tasklist.ToDoTask;
import catbot.ui.Ui;

/**
 * Handles adding a new todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new {@code AddTodoCommand} for a {@code ToDoTask}.
     * @param description is the description of the task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        Task newTask = new ToDoTask(description);
        tasks.add(newTask);
        ui.setNextOutput(
                "Added new task!\n    "
                        + newTask
                        + "\nYou have " + tasks.size()
                        + (tasks.size() > 1 ? " tasks now." : " task now.")
        );
        storage.writeToSaveFile(newTask.toCommand() + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) {
        Task newTask = new ToDoTask(description);
        tasks.add(newTask);
    }
}
