package duke.query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
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
        String desc = query.getParam();
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your deadline!");
        }

        String endDateStr = query.getArgument("/by");
        if (endDateStr == null || endDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide a end date for your deadline!");
        }

        LocalDateTime endDateTime;
        try {
            endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new InvalidCommandParamException(
                    String.format("Please provide a valid end date for your deadline! (%s)", DATETIME_PATTERN));
        }

        Task newTask = tt.addDeadline(desc, endDateTime);

        tt.saveAllTasks();
        return "Added task " + newTask;
    }
}
