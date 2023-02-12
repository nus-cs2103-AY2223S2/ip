package duke.command;

import static duke.command.CommandValidations.validateHasTaskDescription;
import static duke.command.CommandValidations.validateNotEmptyArgs;
import static duke.command.CommandValidations.validateParameterExists;
import static duke.util.Ui.getUi;

import java.time.LocalDate;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.TaskList;

/**
 * A command that creates a DeadlineTask
 * @author Junyi
 */
public class DeadlineCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;
    private final LocalDate deadlineBy;

    /**
     * Constructor for DeadlineCommand.
     * Creates and insert a DeadlineTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     * @param deadlineBy Deadline of task.
     */
    public DeadlineCommand(TaskList taskList, String taskDescription, LocalDate deadlineBy) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        this.deadlineBy = deadlineBy;
    }

    /**
     * Factory method to create deadline command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of DeadlineCommand.
     */
    public static DeadlineCommand createDeadlineCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        validateHasTaskDescription(inputString);
        validateParameterExists(inputString, "/by");
        String deadlineArgs = inputString.substring(9);

        String deadlineDesc = deadlineArgs.split(" /by ")[0];
        LocalDate deadlineBy = LocalDate.parse(deadlineArgs.split(" /by ")[1]);

        return new DeadlineCommand(taskList, deadlineDesc, deadlineBy);
    }

    @Override
    public String execute() throws DukeException {
        DeadlineTask task = new DeadlineTask(taskDescription, deadlineBy);
        taskList.addTask(task);
        System.out.println(taskList.size());

        return getUi().showTaskCreatedMessage(task);
    }
}
