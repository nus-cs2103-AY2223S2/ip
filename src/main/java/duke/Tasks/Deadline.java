package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String FORMAT = "deadline {task name} /by {dd/mm/yyyy HHmm}";
    private LocalDateTime deadline;
    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd LLL, h:mma");

    public Deadline(String desc, String deadline) {
        super(desc);
        setDeadline(deadline);
    }

    private void setDeadline(String deadline) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(deadline, IN_FORMAT);
            this.deadline = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }
    
    private String deadline() {
        return " (by: " + this.deadline.format(OUT_FORMAT) + ")";
    }

    public static String showFormat() {
        return "Create a `deadline` with: " + FORMAT;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.deadline();
    }
}
