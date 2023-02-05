package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.parser.DateTimeParser;
import duke.storage.TaskList;
import duke.task.Deadline;

/**
 * Class use to handle command: add deadline.
 * Allows user to add deadline into the task list.
 */
public class AddDeadlineCommand extends Command {

    private String request;

    /**
     * Constructor to create deadline task according user's request.
     * @param request user's request to be processed into deadline duke.task.
     */
    public AddDeadlineCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {

        String[] req = request.split("deadline ");

        // check presence of task description
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task description");
        }

        req = req[1].split("/by ");

        // check presence of argument
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task deadline");
        }

        String task = req[0].strip();
        String deadline = req[1].strip();

        LocalDateTime dueDate = DateTimeParser.parse(deadline);
        Deadline newDeadline = tasks.addDeadline(task, dueDate);

        return "Great! I've added this task for you \n" + newDeadline
                + "\nYou have " + tasks.numOfTask() + " tasks in the list";


    }
}
