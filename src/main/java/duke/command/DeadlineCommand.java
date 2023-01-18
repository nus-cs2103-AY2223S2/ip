package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

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
     * @throws DukeException Indicates missing cutoff date/time or description in input.
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
     * @throws DukeException Indicates missing cutoff date/time or description in input.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        String[] args = extractValidArgs(input);
        return createDeadline(args);
    }

    private String[] extractValidArgs(String input) throws DukeException {
        String argStr = input.replaceFirst("deadline", "");

        String[] descriptionAndCutoff = argStr.split(" /by ", 2);

        if (descriptionAndCutoff.length != 2) {
            throw new DukeException("The cutoff of a deadline must be specified.");
        }

        descriptionAndCutoff[0] = descriptionAndCutoff[0].trim();
        descriptionAndCutoff[1] = descriptionAndCutoff[1].trim();

        if (descriptionAndCutoff[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (descriptionAndCutoff[1].isEmpty()) {
            throw new DukeException("The cutoff of a deadline must be specified.");
        }

        return descriptionAndCutoff;
    }

    private Deadline createDeadline(String[] args) {
        return new Deadline(false, args[0], args[1]);
    }
}
