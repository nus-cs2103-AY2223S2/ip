package duke.commands;
import duke.dukeexceptions.MissingArgumentException;
import duke.tasks.Event;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to add an event to a task list.
 */
public class EventCommand extends Command {
    private String requestContent;
    public EventCommand(String requestContent) {
        super("EVENT");
        this.requestContent = requestContent;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] splitFrom = requestContent.split(" /from ", 2);
        String description = splitFrom[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitFrom.length != 2) {
            throw new MissingArgumentException("The from cannot be empty.");
        }

        String[] splitTo = splitFrom[1].split(" /to ", 2);
        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        if (from.equals("")) {
            throw new MissingArgumentException("The from cannot be empty.");
        } else if (splitTo.length != 2 || splitTo[1].trim().equals("")) {
            throw new MissingArgumentException("The to cannot be empty.");
        }

        Event newEvent = new Event(description, from, to);
        tasks.addEvent(newEvent);
        String reply = "  Got it. I've added this task:\n"
                + "    " + newEvent.toString()
                + "  Now you have " + tasks.getLen() + " tasks in the list.\n";
        System.out.print(reply);

        return reply;
    }
}
