package command;

import java.time.DateTimeException;
import java.time.LocalDate;

import dukeException.InvalidArgumentException;
import dukeException.MissingArgumentException;
import storage.TaskList;
import task.Deadline;

public class AddDeadlineCommand extends Command {

    private String request;

    /**
     * Constructor to create deadline according user's request
     * @param request user's request to be processed into deadline
     */
    public AddDeadlineCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {
        String[] req = request.split("deadline ");
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task description");
        }
        req = req[1].split("/by ");

        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task deadline");
        }

        String task = req[0].strip();
        String deadline = req[1].trim();

        try {
            LocalDate dueDate = LocalDate.parse(deadline);
            Deadline newDeadline = tasks.addDeadline(task, dueDate);
            return "Great! I've added this task for you \n" + newDeadline
                    + "\nYou have " + tasks.numOfTask() + " tasks in the list";

        } catch (DateTimeException error) {
            throw new InvalidArgumentException("Please insert your date using the format, "
                    + "YYYY-MM-DD (e.g. 2000-01-01)");
        }
    }
}
