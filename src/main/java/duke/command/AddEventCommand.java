package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.task.Event;

public class AddEventCommand extends Command {

    private static final String CMD_KEYWORD = "event";
    private static final String FROM_KEYWORD = "/from";
    private static final String TO_KEYWORD = "/to";

    private final String taskDescription;
    private final String fromStr;
    private final String toStr;

    public AddEventCommand(String userInput) throws DukeException {
        String taskInfo = userInput.replaceFirst(CMD_KEYWORD, "").trim();

        String[] splitByFrom = taskInfo.split(FROM_KEYWORD, 2);
        if (splitByFrom.length == 1) {
            throw new DukeException(String.format("Missing '%s {start time}'.", FROM_KEYWORD));
        }

        taskDescription = splitByFrom[0].trim();
        if (taskDescription.isBlank()) {
            throw new DukeException("Task description cannot be empty.");
        }

        String[] splitByTo = splitByFrom[1].split(TO_KEYWORD, 2);
        if (splitByTo.length == 1) {
            throw new DukeException(String.format("Missing '%s {end time}'.", TO_KEYWORD));
        }

        fromStr = splitByTo[0].trim();
        if (fromStr.isBlank()) {
            throw new DukeException("Start time cannot be empty.");
        }

        toStr = splitByTo[1].trim();
        if (toStr.isBlank()) {
            throw new DukeException("End time cannot be empty.");
        }

    }

    @Override
    public ReturnCode execute(Duke duke) {
        duke.addNewTask(new Event(
                taskDescription,
                duke.parser.parseDateTime(fromStr),
                duke.parser.parseDateTime(toStr)
        ));
        return ReturnCode.SUCCESS;
    }
}
