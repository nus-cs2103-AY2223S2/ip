package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an add deadline command for adding a deadline to a task list.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Creates a deadline using the specified input, adds it to tasks, and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates an error in the input caused by one of the following: missing cutoff date and
     *                       time, missing description, incorrect format, invalid cutoff date and time.
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
     * @throws DukeException Indicates an error in the input caused by one of the following: missing cutoff date and
     *                       time, missing description, incorrect format, invalid cutoff date and time.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        String[] args = extractValidArgs(input);
        return createDeadline(args);
    }

    private String[] extractValidArgs(String input) throws DukeException {
        assert input != null;

        if (!input.matches("deadline .+ /by .+")) {
            throw new DukeException("The deadline command format should be:\n  deadline <description> /by <cutoff>");
        }

        String argStr = input.replaceFirst("deadline", "");
        String[] args = argStr.split(" /by ", 2);

        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        if (args[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }

        if (args[1].isEmpty()) {
            throw new DukeException("The cutoff of a deadline must be specified!");
        }

        if (!args[1].matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX)) {
            throw new DukeException(String.format("The cutoff format should be:\n  %s",
                    LocalDateTimeUtils.INPUT_DATE_TIME_FORMAT));
        }

        return args;
    }

    private Deadline createDeadline(String[] args) throws DukeException {
        assert args != null;
        assert args.length == 2;

        LocalDateTime cutoff;
        try {
            cutoff = LocalDateTime.parse(args[1], LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("The cutoff provided is an invalid date and time!");
        }

        return new Deadline(false, args[0], cutoff);
    }
}
