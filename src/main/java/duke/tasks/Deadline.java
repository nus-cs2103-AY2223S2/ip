package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author Samarth Verma
 */
public class Deadline extends Task {

    private String desc;
    private LocalDate dueDate;

    /**
     * Creates a deadline task.
     *
     * @param id          The id of the deadline.
     * @param description The description of the deadline.
     * @param dueDate     The due date of the deadline.
     */
    public Deadline(int id, String description, String dueDate) {
        super(id);
        desc = description;
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", isDone, description(),
                dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("D|%s|%s|%s|%s", id(), isDone, description(), dueDate);
    }

    /**
     * Deserializes a deadline from a string.
     *
     * @param s The string to deserialize from.
     * @return The deserialized deadline.
     */
    public static Deadline deserialize(String s) {
        String[] parts = s.split("\\|");
        Deadline deadline = new Deadline(Integer.parseInt(parts[1]), parts[3], parts[4]);
        if (parts[2].equals("1")) {
            deadline.markCompleted();
        }
        return deadline;
    }
}
