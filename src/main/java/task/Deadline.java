package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private LocalDate endDate;
    private static final DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;

    }

    public LocalDate getDate() {
        return endDate;
    }

    public  String toText() {
        return "D" + "|" + getNameOfTask() + "|" + (isDone() ? 1 : 0) + "|" + endDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatOfDate.format(endDate) + ")";
    }


}
