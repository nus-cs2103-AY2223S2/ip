package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an add deadline task command.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Add the new deadline specified in the input to tasks and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates an error in the input caused by one of the following: missing cutoff date and/or
     * time, missing description, incorrect format, invalid cutoff date and/or time.
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
     * @throws DukeException Indicates an error in the input caused by one of the following: missing cutoff date and/or
     * time, missing description, incorrect format, invalid cutoff date and/or time.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        String[] args = extractValidArgs(input);
        return createDeadline(args);
    }

    private String[] extractValidArgs(String input) throws DukeException {
        if (!input.matches("deadline .+ /by .+")) {
            throw new DukeException("deadline command should be of the format:\n  deadline <description> /by <cutoff>");
        }

        String argStr = input.replaceFirst("deadline", "");
        String[] args = argStr.split(" /by ", 2);

        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        if (args[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (args[1].isEmpty()) {
            throw new DukeException("The cutoff of a deadline must be specified.");
        }

        if (!args[1].matches(LocalDateTimeUtils.inputDateTimeRegex)) {
            throw new DukeException(String.format("The cutoff of a deadline should be of the format:\n  %s",
                    LocalDateTimeUtils.inputDateTimeFormat));
        }

        return args;
    }

    private Deadline createDeadline(String[] args) throws DukeException {
        LocalDateTime cutoff;
        try {
            cutoff = LocalDateTime.parse(args[1], LocalDateTimeUtils.inputDateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The cutoff of the deadline contains an invalid date and/or time.");
        }

        return new Deadline(false, args[0], cutoff);
    }
}
