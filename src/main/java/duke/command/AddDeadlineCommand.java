package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {

    private static final String CMD_KEYWORD = "deadline";
    private static final String BY_KEYWORD = "/by";

    private final String taskDescription;
    private final String dueByStr;

    public AddDeadlineCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        String[] splitByKeyword = taskInfo.split(BY_KEYWORD, 2);
        if (splitByKeyword.length == 1) {
            throw new DukeException(String.format("Missing '%s {due date}'.", BY_KEYWORD));
        }

        taskDescription = splitByKeyword[0].trim();
        if (taskDescription.isBlank()) {
            throw new DukeException("Task description cannot be empty.");
        }

        dueByStr = splitByKeyword[1].trim();
        if (dueByStr.isBlank()) {
            throw new DukeException("Due date cannot be empty.");
        }
    }

    @Override
    public ReturnCode execute(Duke duke) throws DukeException {
        duke.addNewTask(new Deadline(taskDescription, duke.parser.parseDateTime(dueByStr)));
        return ReturnCode.SUCCESS;
    }
}
