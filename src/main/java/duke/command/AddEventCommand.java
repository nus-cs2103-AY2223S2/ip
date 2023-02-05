package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.parser.DateTimeParser;
import duke.storage.TaskList;
import duke.task.Event;

/**
 * Class use to handle command: add event.
 * Allows user to add event into the task list.
 */
public class AddEventCommand extends Command {

    private String request;

    /**
     * Constructor to add event task according to user's request.
     * @param request user's request to be processed into event duke.task.
     */
    public AddEventCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {

        String[] req = request.split("event ");

        // check missing `task description`
        if (req.length < 2) {
            throw new MissingArgumentException("Missing task description!");
        }

        req = req[1].split("/from ");
        String task = req[0].strip();

        // check missing `task description` and `start date`
        if (task.equals("")) {
            throw new MissingArgumentException("Missing task description!");
        } else if (req.length < 2) {
            throw new MissingArgumentException("Please insert an start date.");
        }

        String[] duration = req[1].split(" /to ");

        // check missing `end date`
        if (duration.length < 2) {
            throw new MissingArgumentException("Please insert an end date.");
        }

        String from = duration[0].strip();
        String to = duration[1].strip();

        // check missing `start date` and ` end date`
        if (from.equals("")) {
            throw new MissingArgumentException("Please insert a start date.");
        } else if (duration.length < 2 || to.equals("")) {
            throw new MissingArgumentException("Please insert an end date.");
        }

        LocalDateTime startDate = DateTimeParser.parse(from);
        LocalDateTime endDate = DateTimeParser.parse(to);
        Event newEvent = tasks.addEvent(task, startDate, endDate);

        // check valid `duration`
        if (startDate.isAfter(endDate)) {
            throw new InvalidArgumentException("Your start date should be before your end date!");
        }

        return "Great! I've added this task for you \n" + newEvent
                + "\nYou have " + tasks.numOfTask() + " tasks in the list";
    }
}
