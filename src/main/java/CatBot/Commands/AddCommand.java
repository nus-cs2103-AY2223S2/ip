package CatBot.Commands;

import CatBot.CatBotException;
import CatBot.Storage.Storage;
import CatBot.TaskList.*;
import CatBot.TaskList.Task.TaskType;
import CatBot.Ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddCommand extends Command {
    private final TaskType taskType;
    private final String description;

    /* Used to store date for Deadline and Event */
    private LocalDateTime date1;

    /* Used to store date for Event */
    private LocalDateTime date2;

    public AddCommand(String description) {
        taskType = TaskType.TODO;
        this.description = description;
    }

    public AddCommand(String description, LocalDateTime dueBy) {
        taskType = TaskType.DEADLINE;
        this.description = description;
        date1 = dueBy;
    }

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
                "Added new task!\n    " +
                newTask +
                "\nYou have " + tasks.size() +
                (tasks.size() > 1 ? " tasks now." : " task now.")
        );
        storage.writeToSaveFile(newTask.toCommand() + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) throws CatBotException{
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
