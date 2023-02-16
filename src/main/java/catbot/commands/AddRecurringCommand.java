package catbot.commands;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.RecurringTask;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles adding a new recurring task to the list
 */
public class AddRecurringCommand extends Command {
    private final String description;
    private final LocalDateTime nextOccurrence;
    private final Duration repeatsEvery;

    /**
     * Constructs a new {@code AddReoccurringCommand} for a {@code DeadlineTask}.
     *
     * @param description    is the description of the task
     * @param nextOccurrence is when the task will happen next
     * @param repeatsEvery   is the time between each occurrence
     */
    public AddRecurringCommand(String description, LocalDateTime nextOccurrence, Duration repeatsEvery) {
        this.description = description;
        this.nextOccurrence = nextOccurrence;
        this.repeatsEvery = repeatsEvery;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        Task newTask = new RecurringTask(description, nextOccurrence, repeatsEvery);
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
        Task newTask = new RecurringTask(description, nextOccurrence, repeatsEvery);
        tasks.add(newTask);
    }
}
