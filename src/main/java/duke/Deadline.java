package duke;


import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String content, String deadline) {
        super(content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format should be yyyy-MM-dd HH:mm");
        }
    }

    public Deadline(String content, LocalDateTime deadline) {
        super(content);
        this.deadline = deadline;
    }

    public Deadline(boolean isMarked, String content, String deadline) {
        super(isMarked, content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format should be yyyy-MM-dd HH:mm");
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D" + d + marked + d + getDescription() + d + deadline.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH a");
        return "[D] " + super.toString() + " (by: " + this.deadline.format(formatter) + ")";
    }
}
