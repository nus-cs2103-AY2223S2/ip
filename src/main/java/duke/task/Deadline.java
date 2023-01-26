package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
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

    public Deadline(boolean isMarked, String content, String deadline) {
        super(isMarked, content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format should be yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D" + d + marked + d + get_content() + d + deadline.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH a");
        return "[D] " + super.toString() + "(by: " + this.deadline.format(formatter) + ")";
    }
}
