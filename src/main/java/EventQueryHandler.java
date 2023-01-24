import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventQueryHandler extends DeadlineQueryHandler {
    public EventQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{ "/from", "/to"});
        String desc = parsed[1];
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your event!");
        }

        String startDateStr = parsed[2];
        if (startDateStr == null || startDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide a start date for the event!");
        }

        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(String.format("Please provide a valid start date for your event! (%s)", DATETIME_PATTERN));
        }

        String endDateStr = parsed[3];
        if (endDateStr == null || endDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide an end date for the event!");
        }

        LocalDateTime endDate;
        try {
            endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(String.format("Please provide a valid end date for your event! (%s)", DATETIME_PATTERN));
        }

        Task newTask = tt.AddEvent(desc, startDate, endDate);
        return "Added task " + newTask;
    }
}
