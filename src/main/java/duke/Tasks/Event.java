package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final String FORMAT = "event {task name}"
                                        + "/from {dd/mm/yyyy HHmm}"
                                        + "/to {dd/mm/yyyy HHmm}";
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd LLL, h:mma");


    public Event(String desc, String from, String to) {
        super(desc);
        setFrom(from);
        setTo(to);
    }


    private void setFrom(String from) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(from, IN_FORMAT);
            this.from = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }

    private void setTo(String to) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(to, IN_FORMAT);
            this.to = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }

    private String duration() {
        return " (from: " + this.from.format(OUT_FORMAT) + ", to: "
                + this.to.format(OUT_FORMAT) + ")";
    }

    public static String showFomat() {
        return "Create an `Event` with: " + FORMAT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.duration();
    }
    
}
