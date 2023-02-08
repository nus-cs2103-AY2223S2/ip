package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.parser.DateTimeParser;
import duke.parser.InputValidator;
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

    /**
     * Execute the <code>Event</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after added the task into task list.
     * @throws MissingArgumentException
     * @throws InvalidArgumentException
     */
    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {
        String[] processedRequest = InputValidator.normaliseEventRequest(request);

        String description = processedRequest[0];
        String from = processedRequest[1];
        String to = processedRequest[2];

        LocalDateTime startDate = DateTimeParser.parse(from);
        LocalDateTime endDate = DateTimeParser.parse(to);
        Event newEvent = tasks.addEvent(description, startDate, endDate);

        // checks valid `duration`
        if (startDate.isAfter(endDate)) {
            throw new InvalidArgumentException("Your start date should be before your end date!");
        }

        String response = String.format("Great! I've added this task for you\n %s \n"
                + "You have %d tasks in the list.", newEvent, tasks.numOfTask());

        return response;
    }
}
