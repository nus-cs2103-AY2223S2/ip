package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    private static final String CMD_KEYWORD = "todo";

    private final String taskDescription;
    public AddTodoCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        taskDescription = taskInfo;
        if (taskDescription.isBlank()) {
            throw new DukeException("Task description cannot be empty.");
        }

    }

    @Override
    public ReturnCode execute(Duke duke) {
        duke.addNewTask(new Todo(taskDescription));
        return ReturnCode.SUCCESS;
    }
}
