package duke.task;

import duke.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String description;
    private LocalDateTime deadline;

    private Deadline(String description, LocalDateTime deadline) {
        super(TaskType.DEADLINE, description);
        this.description = description;
        this.deadline = deadline;
    }

    public static Deadline to(String str) {
        String target = " /by ";
        LocalDateTime dateTime;
        String description, deadline, day, month;
        int index = str.indexOf(target);
        description = str.substring(0, index);
        deadline = str.substring(index + 5);
        int firstSlash = deadline.indexOf("/");
        int secondSlash = deadline.indexOf("/", firstSlash + 1);
        day = firstSlash == 1 ? "d" : "dd";
        month = secondSlash - firstSlash == 2 ? "M" : "MM";
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(day + "/" + month + "/yyyy HHmm");
        try {
            dateTime = LocalDateTime.parse(deadline, inFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }
        return new Deadline(description, dateTime);
    }

    @Override
    public String taskToSavedForm() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "deadline " + this.description + " /by " + this.deadline.format(outFormatter) + marked;
    }

    @Override
    public String toString() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String str = super.toString();
        str += " " + "(by: " + this.deadline.format(outFormatter) + ")";
        return str;
    }
}
