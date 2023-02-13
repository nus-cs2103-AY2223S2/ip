package chad.command;

import java.time.LocalDateTime;

import chad.exception.InvalidArgumentException;
import chad.exception.MissingArgumentException;
import chad.parser.DateTimeParser;
import chad.parser.InputValidator;
import chad.storage.TaskList;
import chad.task.Event;

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

        // checks valid `duration`
        if (startDate.isAfter(endDate)) {
            throw new InvalidArgumentException("Your start date should be before your end date!");
        }

        Event duplicateChecker = new Event(description, startDate, endDate);
        if (InputValidator.checkDuplicates(tasks, duplicateChecker)) {
            return String.format("You have already added this into your task list.\n Duplicated Task: %s",
                    duplicateChecker.toString());
        }

        Event newEvent = tasks.addEvent(description, startDate, endDate);
        String response = String.format("Great! I've added this task for you\n %s \n"
                + "You have %d tasks in the list.", newEvent, tasks.numOfTask());

        return response;
    }
}
