package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.storage.TaskList;
import duke.task.Deadline;

/**
 * Class use to handle command: add deadline.
 * Allows user to add deadline into the duke.task list.
 */
public class AddDeadlineCommand extends Command {

    private String request;

    /**
     * Constructor to create deadline duke.task according user's request.
     * @param request user's request to be processed into deadline duke.task.
     */
    public AddDeadlineCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {
        String[] req = request.split("deadline ");
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the duke.task description");
        }
        req = req[1].split("/by ");

        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the duke.task deadline");
        }

        String task = req[0].strip();
        String deadline = req[1].strip();

        try {
            LocalDate dueDate = LocalDate.parse(deadline);
            Deadline newDeadline = tasks.addDeadline(task, dueDate);
            return "Great! I've added this duke.task for you \n" + newDeadline
                    + "\nYou have " + tasks.numOfTask() + " tasks in the list";

        } catch (DateTimeException error) {
            throw new InvalidArgumentException("Please insert your date using the format, "
                    + "YYYY-MM-DD (e.g. 2000-01-01)");
        }
    }
}
