package duke.commands;
import duke.dukeexceptions.MissingArgumentException;
import duke.tasks.Deadline;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to add a deadline.
 */
public class DeadlineCommand extends Command {
    private String requestContent;
    public DeadlineCommand(String requestContent) {
        super("DEADLINE");
        this.requestContent = requestContent;
    }

    /**
     * Adds deadline to a task list.
     */
    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String description = splitWithBy[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitWithBy.length != 2 || splitWithBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }

        String by = splitWithBy[1].trim();
        Deadline newDeadline = new Deadline(description, by);
        tasks.addDeadline(newDeadline);

        String reply = "Got it. I've added this task:\n"
                + "  " + newDeadline.toString()
                + "Now you have " + tasks.getLen() + " tasks in the list.\n";
        System.out.print(reply);
        return reply;

    }
}
