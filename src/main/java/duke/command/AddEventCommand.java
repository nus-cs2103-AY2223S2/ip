package duke.command;

import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.DukeException;
import duke.task.Event;

/**
 * Handles a request to add an event. Parameters of event should be provided.
 */
public class AddEventCommand extends Command {

    /** Keyword of this command */
    private static final String CMD_KEYWORD = "event";

    private static final String FROM_KEYWORD = "/from";
    private static final String TO_KEYWORD = "/to";

    private final String taskDescription;
    private final String fromStr;
    private final String toStr;

    /**
     * Constructs an instance of AddEventCommand.
     *
     * @param userInput String containing the whole input provided by the user.
     * @throws DukeException If missing FROM_KEYWORD.
     *                       If missing TO_KEYWORD.
     *                       If task description is empty.
     *                       If start time is empty.
     *                       If end time is empty.
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public ReturnCode execute(Duke duke) {
        try {
            duke.addNewTask(new Event(
                    taskDescription,
                    duke.dateTimeParser.parseDateTime(fromStr),
                    duke.dateTimeParser.parseDateTime(toStr)
            ));
            return ReturnCode.SUCCESS;
        } catch (DateTimeParseException e) {
            duke.ui.warn("Sorry, I do not understand the date/time you just provided.");
            duke.ui.println("Please provide in 'YYYY-MM-DD HH:MM:SS' or 'YYYY-MM-DD HH:MM' format.");
            return ReturnCode.FAILURE;
        }
    }
}
