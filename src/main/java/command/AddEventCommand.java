package command;

import java.time.DateTimeException;
import java.time.LocalDate;

import dukeException.InvalidArgumentException;
import dukeException.MissingArgumentException;
import storage.TaskList;
import task.Event;

public class AddEventCommand extends Command {

    private String request;

    /**
     * Constructor to add event task according to user's request.
     * @param request user's request to be processed into event task.
     */
    public AddEventCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {
        String[] req = request.split("event ");

        if (req.length < 2) {
            throw new MissingArgumentException("Missing task description!");
        }

        req = req[1].split("/from ");
        String task = req[0];

        if (task.equals("")) {
            throw new MissingArgumentException("Missing task description!");
        } else if (req.length < 2) {
            throw new MissingArgumentException("Please insert an start date.");
        }

        String[] duration = req[1].split(" /to ");

        if (duration.length < 2) {
            throw new MissingArgumentException("Please insert an end date.");
        }

        String from = duration[0].trim();
        String to = duration[1].trim();

        if (from.equals("")) {
            throw new MissingArgumentException("Please insert a start date.");
        } else if (duration.length < 2 || to.equals("")) {
            throw new MissingArgumentException("Please insert an end date.");
        }

        try {
            LocalDate startDate = LocalDate.parse(from);
            LocalDate endDate = LocalDate.parse(to);
            Event newEvent = tasks.addEvent(task, startDate, endDate);

            if (startDate.isAfter(endDate)) {
                throw new InvalidArgumentException("Your start date should be before your end date!");
            }

            return "Great! I've added this task for you \n" + newEvent
                    + "\nYou have " + tasks.numOfTask() + " tasks in the list";
        } catch (DateTimeException error) {
            throw new InvalidArgumentException("Wrong date format! "
                    + "Please follow the format YYYY-MM-DD (e.g. 2000-01-01)");
        }
    }
}
