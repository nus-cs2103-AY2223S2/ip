package duke.command;

import static duke.command.CommandValidations.validateHasTaskDescription;
import static duke.command.CommandValidations.validateNotEmptyArgs;
import static duke.util.Ui.getUi;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * A command that creates a TodoTask
 * @author Junyi
 */
public class TodoCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;

    /**
     * Constructor for TodoCommand.
     * Creates and insert a TodoTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     */
    public TodoCommand(TaskList taskList, String taskDescription) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
    }

    /**
     * Factory method to create todo command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of TodoCommand.
     */
    public static TodoCommand createTodoCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        validateHasTaskDescription(inputString);
        String description = inputString.substring(5);

        return new TodoCommand(taskList, description);
    }

    @Override
    public String execute() throws DukeException {
        TodoTask task = new TodoTask(taskDescription);
        taskList.addTask(task);
        return getUi().showTaskCreatedMessage(task);
    }
}
