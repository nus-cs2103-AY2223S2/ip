package alfred.command;

import java.time.format.DateTimeParseException;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.task.ToDo;
import alfred.ui.Ui;

/**
 * Represents the add command when a user wishes to add a task.
 */
public class AddCommand extends Command {
    private String fullCommand;

    /**
     * Constructs an add command with the given command.
     * @param fullCommand The full command to determine what is to be added.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        String[] fullCommandArr = fullCommand.split(" ", 2);
        String typeTask = fullCommandArr[0];
        String[] descriptionArr;
        String[] lineArr;
        Task task;
        // Handling the type of task
        switch (typeTask) {
        case "todo":
            if (fullCommandArr.length == 1) {
                throw new AlfredException("The description of a todo cannot be empty\n");
            }
            task = new ToDo(fullCommandArr[1]);
            tasks.addTask(task);
            break;
        case "deadline": // Need to consider what if no '/' is given
            lineArr = fullCommand.split("/by ");
            if (lineArr.length == 1) {
                throw new AlfredException("Deadlines should have a due date ."
                        + "Eg: \"<TaskName> /by <DueDate>\"\n");
            }
            descriptionArr = lineArr[0].split(" ", 2);
            if (descriptionArr.length == 1) {
                throw new AlfredException("Deadlines should have a due date ."
                        + "Eg: \"<TaskName> /by <DueDate>\"\n");
            }
            try {
                task = new Deadline(descriptionArr[1], lineArr[1]);
                tasks.addTask(task);
                break;
            } catch (DateTimeParseException e) {
                throw new AlfredException("The date format should be given as dd/mm/yyyy 24hr-time\n");
            }
        case "event": // need to consider what if no '/from' and '/or' is not given?
            lineArr = fullCommand.split("/from | /to ");
            if (lineArr.length < 2) { // not sure how to check if there's /from and /to
                throw new AlfredException("Events should have start and end time. "
                        + "Eg: \"<EventName> /from <StartTime> /to <EndTime>\"\n");
            }
            descriptionArr = lineArr[0].split(" ", 2);
            if (descriptionArr.length == 1) {
                throw new AlfredException("Events should have start and end time. "
                        + "Eg: \"<EventName> /from <StartTime> /to <EndTime>\"\n");
            }
            try {
                task = new Event(descriptionArr[1], lineArr[1], lineArr[2]);
                tasks.addTask(task);
                break;
            } catch (DateTimeParseException e) {
                throw new AlfredException("The date format should be given as dd/mm/yyyy 24hr-time\n");
            }
        default:
            throw new AlfredException("I'm sorry, but I don't know what that means :<\n");
        }

        assert tasks.getSize() > 0 : "Failed to add task";

        // Alfred's response to remaining tasks
        String numTasks = tasks.getSize() == 1 ? "task" : "tasks";
        String output = String.format("Noted, task added: \n      %s\n"
                + "    Number of %s in the list: %d\n", task, numTasks, tasks.getSize());
        return ui.getCommandMessage(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
