package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate dueDate;
    public Deadlines(String name, String dueDate) {
        super(name, "D");
        this.dueDate = LocalDate.parse(dueDate);
    }

    public Deadlines(String name, String dueDate, boolean isDone) {
        super(name, "D", isDone);
        this.dueDate = LocalDate.parse(dueDate);
    }

//    protected static LocalDateTime convertToDateFormat(String date) {
//        int year = Integer.parseInt(date.split("-")[0]);
//        int month = Integer.parseInt(date.split("-")[1]);
//        int day = Integer.parseInt(date.split("-")[2]);
//        return
//    }

    @Override
    public String description() {
        return String.format("%s (by: %s)", super.description(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String formattedDescription() {
        return super.formattedDescription()
                + String.format(" | %s", dueDate.toString());
    }
}
