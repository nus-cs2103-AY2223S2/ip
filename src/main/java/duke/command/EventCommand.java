package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an add event command for adding an event to a task list.
 */
public class EventCommand extends AddCommand {
    /**
     * Creates an event using the specified input, adds it to tasks, and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates an error in the input caused by one of the following: missing start or end of
     *                       event, missing description, incorrect format, invalid start or end of event.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        return super.run(input, tasks);
    }

    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates an error in the input caused by one of the following: missing start or end of
     *                       event, missing description, incorrect format, invalid start or end of event.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        String[] args = extractValidArgs(input);
        return createEvent(args);
    }

    private String[] extractValidArgs(String input) throws DukeException {
        validateCommandFormat(input);

        String[] args = extractArgs(input);

        validateNonEmptyDescription(args);
        validateNonEmptyStart(args);
        validateNonEmptyEnd(args);

        validateStartAndEndFormat(args);

        return args;
    }

    private void validateCommandFormat(String input) throws DukeException {
        if (!input.matches("event .+ /from .+ /to .+")) {
            throw new DukeException("The event command format should be:\n  event <description> /from <start of event> "
                    + "/to <end of event>");
        }
    }

    private String[] extractArgs(String input) {
        String argStr = input.replaceFirst("event ", "");
        String[] args = argStr.split("( /from | /to )", 3);

        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        return args;
    }

    private void validateNonEmptyDescription(String[] args) throws DukeException {
        if (args[0].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty!");
        }
    }

    private void validateNonEmptyStart(String[] args) throws DukeException {
        if (args[1].isEmpty()) {
            throw new DukeException("The start of an event must be specified!");
        }
    }

    private void validateNonEmptyEnd(String[] args) throws DukeException {
        if (args[2].isEmpty()) {
            throw new DukeException("The end of an event must be specified!");
        }
    }

    private void validateStartAndEndFormat(String[] args) throws DukeException {
        if (!args[1].matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX)
                || !args[2].matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX)) {

            throw new DukeException(String.format("Start and end of event should be of the format:\n  %s",
                    LocalDateTimeUtils.INPUT_DATE_TIME_FORMAT));
        }
    }

    private Event createEvent(String[] args) throws DukeException {
        String description = args[0];
        LocalDateTime start = extractValidStart(args);
        LocalDateTime end = extractValidEnd(args);

        return new Event(false, description, start, end);
    }

    private LocalDateTime extractValidStart(String[] args) throws DukeException {
        try {
            return LocalDateTime.parse(args[1], LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("The start of the event provided is an invalid date and time!");
        }
    }

    private LocalDateTime extractValidEnd(String[] args) throws DukeException {
        try {
            return LocalDateTime.parse(args[2], LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("The end of the event provided is an invalid date and time!");
        }
    }
}
