package duke.model;

import duke.common.exception.DukeRuntimeException;
import duke.model.command.Command;
import duke.model.task.TaskList;
import duke.util.container.ExecutionResult;
import duke.util.parser.CommandParser;

/**
 * Represents the model that controls the logic of the application.
 */
public class Model {

    private TaskList taskList;

    /**
     * Creates a new {@code Model} to control the application.
     *
     * @param taskList the underlying {@code TaskList} that stores the managed tasks
     */
    public Model(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Executes a command, given as an input string.
     *
     * @param input the given command, as a string
     * @return the result of executing the command
     */
    public ExecutionResult execute(String input) {
        try {
            Command command = CommandParser.of(input).parse();
            String msg = command.execute(taskList);
            boolean isExit = command.isExit();
            return new ExecutionResult(msg, isExit);
        } catch (DukeRuntimeException ex) {
            return new ExecutionResult(ex.getMessage(), false);
        }
    }
}
