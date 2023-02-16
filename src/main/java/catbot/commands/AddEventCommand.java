package catbot.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.EventTask;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles adding a new event task to the list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime startsAt;
    private final LocalDateTime endsAt;

    /**
     * Constructs a new {@code AddEventCommand} for a {@code EventTask}.
     *
     * @param description is the description of the task.
     * @param startsAt    is the time at which the event starts.
     * @param endsAt      is the time at which the event ends.
     */
    public AddEventCommand(String description, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.description = description;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        Task newTask = new EventTask(description, startsAt, endsAt);
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
        Task newTask = new EventTask(description, startsAt, endsAt);
        tasks.add(newTask);
    }
}
