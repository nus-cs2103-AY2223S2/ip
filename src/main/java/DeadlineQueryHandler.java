import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class DeadlineQueryHandler extends TaskQueryHandler {
    protected final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";

    public DeadlineQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{"/by"});

        String desc = parsed[1];
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your deadline!");
        }

        String endDateStr = parsed[2];
        if (endDateStr == null || endDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide a end date for your deadline!");
        }

        LocalDateTime endDateTime;

        try {
            endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new InvalidCommandParamException(String.format("Please provide a valid end date for your deadline! (%s)", DATETIME_PATTERN));
        }

        Task newTask = tt.AddDeadline(desc, endDateTime);

        return "Added task " + newTask;
    }
}
