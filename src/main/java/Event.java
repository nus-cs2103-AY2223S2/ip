import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDate start;
    private final LocalDate end;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String command, String start, String end) {
        super(command);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start.format(formatter) + "to:" + end.format(formatter) + ")";
    }

    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[E] | %d | %s | %s | %s",
                done,
                task,
                this.start,
                this.end);
    }

}
