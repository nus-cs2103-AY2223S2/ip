package wessy.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone){
        super(description, isDone);
        this.by=by;
    }

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toString(by) + ")";
    }

    public static String toString(LocalDateTime dateTime) {
        String str = dateTime.toString();
        if (str.substring(11).equals("12:34:56")) {
            return str.substring(0, 10);
        }
        return str.substring(0, 10) + " " + str.substring(11, 16);
    }

    @Override
    public String saveAsStr(String separator) {
        return "D" + super.saveAsStr(separator) + separator + by + "\n";
    }
}