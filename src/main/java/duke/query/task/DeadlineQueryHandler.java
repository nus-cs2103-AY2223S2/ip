package duke.query.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.query.Query;
import duke.query.exception.InvalidCommandParamException;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The DeadlineQueryHandler class handles user queries for adding new deadlines.
 */
public class DeadlineQueryHandler extends TaskQueryHandler {
    protected static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";

    public DeadlineQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for adding a new deadline.
     *
     * @param query user query
     * @return response from adding deadline
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String desc = getNotBlankParam(query, "Please provide a description for your deadline!");
        LocalDateTime endDateTime = getLocalDateTimeFromQuery(query, "/by", "end date");

        Task newTask = tt.addDeadline(desc, endDateTime);

        tt.saveAllTasks();
        return "Added \n" + newTask;
    }

    protected LocalDateTime getLocalDateTimeFromQuery(Query query, String argKey, String expectedParam)
            throws InvalidCommandParamException {
        String dateStr = getNotBlankArg(query, argKey, getInvalidCommandResponse(expectedParam));
        return getLocalDateTimeFromDateStr(dateStr, expectedParam);
    }

    protected LocalDateTime getLocalDateTimeFromDateStr(String dateStr, String expectedParam)
            throws InvalidCommandParamException {
        try {
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(
                    getInvalidCommandResponse(expectedParam, DATETIME_PATTERN));
        }
    }

    protected static String getInvalidCommandResponse(String expectedParam) {
        return getInvalidCommandResponse(expectedParam, "");
    }

    protected static String getInvalidCommandResponse(String expectedParam, String correctFormat) {
        return String.format("Please provide a valid %s! %s", expectedParam, correctFormat);
    }

    @Override
    public String getQueryDescription() {
        return "deadline \n- Adds a new deadline.";
    }

    @Override
    public String getQuerySyntax() {
        return String.format("deadline <description> /by <%s>", DATETIME_PATTERN);
    }
}
