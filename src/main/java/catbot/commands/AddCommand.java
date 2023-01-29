package catbot.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.DeadlineTask;
import catbot.tasklist.EventTask;
import catbot.tasklist.Task;
import catbot.tasklist.Task.TaskType;
import catbot.tasklist.TaskList;
import catbot.tasklist.ToDoTask;
import catbot.ui.Ui;


/**
 * Handles adding a new task to the task list.
 */
public class AddCommand extends Command {
    private final TaskType taskType;
    private final String description;

    /* Used to store date for Deadline and Event */
    private LocalDateTime date1;

    /* Used to store date for Event */
    private LocalDateTime date2;

    /**
     * Constructs a new {@code AddCommand} for a {@code ToDoTask}.
     * @param description is the description of the task.
     */
    public AddCommand(String description) {
        taskType = TaskType.TODO;
        this.description = description;
    }

    /**
     * Constructs a new {@code AddCommand} for a {@code DeadlineTask}.
     * @param description is the description of the task.
     * @param dueBy is the date and time by which the task is due.
     */
    public AddCommand(String description, LocalDateTime dueBy) {
        taskType = TaskType.DEADLINE;
        this.description = description;
        date1 = dueBy;
    }

    /**
     * Constructs a new {@code AddCommand} for a {@code EventTask}.
     * @param description is the description of the task.
     * @param startsAt is the time at which the event starts.
     * @param endsAt is the time at which the event ends.
     */
    public AddCommand(String description, LocalDateTime startsAt, LocalDateTime endsAt) {
        taskType = TaskType.EVENT;
        this.description = description;
        date1 = startsAt;
        date2 = endsAt;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new ToDoTask(description);
            break;
        case DEADLINE:
            newTask = new DeadlineTask(description, date1);
            break;
        case EVENT:
            newTask = new EventTask(description, date1, date2);
            break;
        default:
            throw new CatBotException("This should be impossible to reach but I have to add this because Java™");
        }

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
    public void loadCommand(ArrayList<Task> tasks) throws CatBotException {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new ToDoTask(description);
            break;
        case DEADLINE:
            newTask = new DeadlineTask(description, date1);
            break;
        case EVENT:
            newTask = new EventTask(description, date1, date2);
            break;
        default:
            throw new CatBotException("This should be impossible to reach but I have to add this because Java™");
        }
        tasks.add(newTask);
    }
}
