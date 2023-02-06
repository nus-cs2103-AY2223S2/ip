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
    private static final int EXPECTED_ARG_COUNT = 2;

    private static final int DESCRIPTION_ARG_INDEX = 0;
    private static final int CUTOFF_ARG_INDEX = 1;

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
        validateCommandFormat(input);

        String[] args = extractArgs(input);

        validateNonEmptyDescription(args);
        validateNonEmptyCutoff(args);

        validateCutoffFormat(args);

        return args;
    }

    private void validateCommandFormat(String input) throws DukeException {
        assert input != null;

        if (!input.matches("deadline .+ /by .+")) {
            throw new DukeException("The deadline command format should be:\n  deadline <description> /by <cutoff>");
        }
    }

    private String[] extractArgs(String input) {
        assert input != null;

        String argStr = input.replaceFirst("deadline", "");
        String[] args = argStr.split(" /by ", 2);

        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        return args;
    }

    private void validateNonEmptyDescription(String[] args) throws DukeException {
        assert args != null;
        assert args.length >= DESCRIPTION_ARG_INDEX + 1;

        if (args[DESCRIPTION_ARG_INDEX].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }
    }

    private void validateNonEmptyCutoff(String[] args) throws DukeException {
        assert args != null;
        assert args.length >= CUTOFF_ARG_INDEX + 1;

        if (args[CUTOFF_ARG_INDEX].isEmpty()) {
            throw new DukeException("The cutoff of a deadline must be specified!");
        }
    }

    private void validateCutoffFormat(String[] args) throws DukeException {
        assert args != null;
        assert args.length >= CUTOFF_ARG_INDEX + 1;

        if (!args[CUTOFF_ARG_INDEX].matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX)) {
            throw new DukeException(String.format("The cutoff format should be:\n  %s",
                    LocalDateTimeUtils.INPUT_DATE_TIME_FORMAT));
        }
    }

    private Deadline createDeadline(String[] args) throws DukeException {
        assert args != null;
        assert args.length >= EXPECTED_ARG_COUNT;

        String description = args[DESCRIPTION_ARG_INDEX];
        LocalDateTime cutoff = extractValidCutoff(args);

        return new Deadline(false, description, cutoff);
    }

    private LocalDateTime extractValidCutoff(String[] args) throws DukeException {
        assert args != null;
        assert args.length >= CUTOFF_ARG_INDEX + 1;

        try {
            return LocalDateTime.parse(args[CUTOFF_ARG_INDEX], LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("The cutoff provided is an invalid date and time!");
        }
    }
}
