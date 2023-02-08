package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.task.Todo;

/**
 * Handles a request to add an todo. Parameters of todo should be provided.
 */
public class AddTodoCommand extends Command {

    /** Keyword of this command */
    private static final String CMD_KEYWORD = "todo";

    private final String taskDescription;

    /**
     * Constructs an instance of AddTodoCommand.
     *
     * @param userInput String containing the whole input provided by the user.
     * @throws DukeException If task description is empty.
     */
    public AddTodoCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        taskDescription = taskInfo;
        if (taskDescription.isBlank()) {
            throw new DukeException("Task description cannot be empty.");
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public ReturnCode execute(Duke duke) {
        duke.addNewTask(new Todo(taskDescription));
        return ReturnCode.SUCCESS;
    }
}
