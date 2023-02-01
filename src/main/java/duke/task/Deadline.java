package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.TaskType;

/**
 * Tasks that have to be done by a certain deadline.
 */
public class Deadline extends Task {
    private String description;
    private LocalDateTime deadline;

    private Deadline(String description, LocalDateTime deadline) {
        super(TaskType.DEADLINE, description);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Factory method of Deadline objects.
     * @param str String containing deadline description and date and time of deadline.
     * @return Deadline object.
     */
    public static Deadline to(String str) {
        String target = " /by ";
        LocalDateTime dateTime;
        int index = str.indexOf(target);
        String description = str.substring(0, index);
        String deadline = str.substring(index + 5);
        int firstSlash = deadline.indexOf("/");
        int secondSlash = deadline.indexOf("/", firstSlash + 1);
        String day = firstSlash == 1 ? "d" : "dd";
        String month = secondSlash - firstSlash == 2 ? "M" : "MM";
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(day + "/" + month + "/yyyy HHmm");
        try {
            dateTime = LocalDateTime.parse(deadline, inFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }
        return new Deadline(description, dateTime);
    }

    /**
     * Creates a string representing the deadline object so that it can be saved by Storage object.
     * @return String representing the deadline object.
     */
    @Override
    public String taskToSavedForm() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "deadline " + this.description + " /by " + this.deadline.format(outFormatter) + marked;
    }

    /**
     * Creates custom string containing Deadline object's description and date and time of deadline.
     * @return String representing Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String str = super.toString();
        str += " " + "(by: " + this.deadline.format(outFormatter) + ")";
        return str;
    }
}
