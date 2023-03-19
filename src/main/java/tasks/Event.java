package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents Event Task
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu");
    private LocalDate from;
    private LocalDate to;


    /**
     * Execute adding an Event command
     * @param eventName
     * @param from
     * @param to
     */
    public Event(String eventName, String from, String to) {
        super(eventName);
        String[] fromDate = from.split(" ", 2);
        LocalDate ld1 = LocalDate.parse(fromDate[1].replace(" ", ""));
        String[] toDate = to.split(" ", 2);
        LocalDate ld2 = LocalDate.parse(toDate[1].replace(" ", ""));
        this.from = ld1;
        this.to = ld2;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from.format(FORMAT) + " to " + to.format(FORMAT) + ")";
    }
}
