package duke.query.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.query.InvalidCommandParamException;
import duke.query.Query;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The EventQueryHandler class handles user queries for adding new events.
 */
public class EventQueryHandler extends DeadlineQueryHandler {
    public EventQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for adding an event.
     *
     * @param query user query
     * @return response from adding an event
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String desc = query.getParam();
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException(getInvalidCommandResponse("a description"));
        }

        String startDateStr = query.getArgument("/from");
        if (startDateStr == null || startDateStr.isBlank()) {
            throw new InvalidCommandParamException(getInvalidCommandResponse("a start date"));
        }

        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(getInvalidCommandResponse("a valid start date", DATETIME_PATTERN));
        }

        String endDateStr = query.getArgument("/to");
        if (endDateStr == null || endDateStr.isBlank()) {
            throw new InvalidCommandParamException(getInvalidCommandResponse("an end date"));
        }

        LocalDateTime endDate;
        try {
            endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(getInvalidCommandResponse("a valid end date", DATETIME_PATTERN));
        }

        Task newTask = tt.addEvent(desc, startDate, endDate);
        tt.saveAllTasks();
        return "Added task " + newTask;
    }

    private static String getInvalidCommandResponse(String expectedParam) {
        return getInvalidCommandResponse(expectedParam, "");
    }

    private static String getInvalidCommandResponse(String expectedParam, String correctFormat) {
        return String.format("Please provide %s for your event! %s", expectedParam, correctFormat);
    }
}
