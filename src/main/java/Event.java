import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String objective, LocalDateTime from, LocalDateTime to) {
        super(objective);
        this.from = from;
        this.to = to;
    }

    public static Event parseArgs(String[] args) throws TaskParseException {
        String objective = "";
        String from = "";
        String to = "";
        boolean token_from = false;
        boolean token_to = false;
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) throw new TaskParseException("This event has too many start-times!");
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) throw new TaskParseException("This event has too many end-times!");
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/from")) {
                token_from = true;
                token_to = false;
                continue;
            }
            if (args[i].equals("/to")) {
                token_from = false;
                token_to = true;
                continue;
            }
            if (token_from) {
                from += (from.isEmpty() ? "" : " ") + args[i];
            } else if (token_to) {
                to += (to.isEmpty() ? "" : " ") + args[i];
            } else {
                objective += (objective.isEmpty() ? "" : " ") + args[i];
            }
        }
        if (objective.isEmpty()) throw new TaskParseException("This event is missing its body text!");
        if (from.isEmpty()) throw new TaskParseException("This event is missing its start-time! Use /from [date]");
        if (to.isEmpty()) throw new TaskParseException("This event is missing its end-time! Use /to [date]");
        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(from, DATE_IN_FMT);
        } catch (DateTimeParseException ex) {
            throw new TaskParseException(from + " needs to be formatted as " + DATE_IN_FMT_STR + "!");
        }
        try {
            toDate = LocalDateTime.parse(to, DATE_IN_FMT);
        } catch (DateTimeParseException ex) {
            throw new TaskParseException(to + " needs to be formatted as " + DATE_IN_FMT_STR + "!");
        }
        return new Event(objective, fromDate, toDate);
    }

    @Override
    public boolean beforeDate(LocalDateTime date) {
        return from.isBefore(date) || from.isEqual(date);
    }

    @Override
    public boolean afterDate(LocalDateTime date) {
        return to.isAfter(date) || to.isEqual(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from.format(DATE_OUT_FMT) + " - " + to.format(DATE_OUT_FMT) + ")";
    }
}
