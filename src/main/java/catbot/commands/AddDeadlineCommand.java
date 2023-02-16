package catbot.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.DeadlineTask;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles adding a new deadline task to the list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime dueBy;

    /**
     * Constructs a new {@code AddDeadlineCommand} for a {@code DeadlineTask}.
     *
     * @param description is the description of the task.
     * @param dueBy       is the date and time by which the task is due.
     */
    public AddDeadlineCommand(String description, LocalDateTime dueBy) {
        this.description = description;
        this.dueBy = dueBy;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        Task newTask = new DeadlineTask(description, dueBy);
        tasks.add(newTask);
        ui.setNextOutput("Added new task!\n    "
                + newTask
                + "\nYou have " + tasks.size()
                + (tasks.size() > 1 ? " tasks now." : " task now.")
        );
        storage.writeToSaveFile(newTask.toCommand() + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) {
        Task newTask = new DeadlineTask(description, dueBy);
        tasks.add(newTask);
    }
}
