package Command;

import DukeException.MissingArgumentException;
import Storage.TaskList;
import Task.Deadline;

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
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] req = this.request.split(" ");
        req = request.split("deadline")[1].split("/by");
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing either the task or the deadline");
        }
        String task = req[0];
        String deadline = req[1];
        Deadline newDeadline = tasks.addDeadline(task, deadline);
        return "Great! I've added this task for you \n" + newDeadline +
                "\nYou have " + tasks.numOfTask() + " tasks in the list";
    }
}
