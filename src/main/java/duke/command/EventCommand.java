package duke.command;

import static duke.command.CommandValidations.validateHasTaskDescription;
import static duke.command.CommandValidations.validateNotEmptyArgs;
import static duke.command.CommandValidations.validateParameterExists;
import static duke.util.Ui.getUi;

import java.time.LocalDate;

import duke.DukeException;
import duke.task.EventTask;
import duke.task.TaskList;

/**
 * A command that creates an EventTask
 * @author Junyi
 */
public class EventCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;
    private final LocalDate eventFrom;
    private final LocalDate eventBy;

    /**
     * Constructor for DeadlineCommand.
     * Creates and insert a EventTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     * @param eventFrom Start date of task.
     * @param eventBy End date of task.
     */
    public EventCommand(TaskList taskList, String taskDescription, LocalDate eventFrom, LocalDate eventBy) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        this.eventFrom = eventFrom;
        this.eventBy = eventBy;
    }

    /**
     * Factory method to create event command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of EventCommand.
     */
    public static EventCommand createEventCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        validateHasTaskDescription(inputString);
        validateParameterExists(inputString, "/from");
        validateParameterExists(inputString, "/to");
        String eventArgs = inputString.substring(6);

        String[] firstSplit = eventArgs.split(" /from ");
        String[] secondSplit = firstSplit[1].split(" /to ");

        String eventDesc = firstSplit[0];
        LocalDate eventFrom = LocalDate.parse(secondSplit[0]);
        LocalDate eventBy = LocalDate.parse(secondSplit[1]);

        return new EventCommand(taskList, eventDesc, eventFrom, eventBy);
    }

    @Override
    public String execute() throws DukeException {
        EventTask task = new EventTask(taskDescription, eventFrom, eventBy);
        taskList.addTask(task);
        return getUi().showTaskCreatedMessage(task);
    }
}
