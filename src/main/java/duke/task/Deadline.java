package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Deadline task in Duke.
 */
public class Deadline extends Task {

    private static final String TYPE_TO_STRING = "D";
    private final LocalDateTime deadline;


    /**
     * Represents a Deadline task in Duke.
     *
     * @param task the task details.
     * @param deadline the deadline the task should be completed by.
     * @throws DateTimeParseException thrown when the date and time given is not parsable.
     */
    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        this.setType(Types.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Represents a Deadline task in Duke.
     *
     * @param data an array of Strings with relevant information typically obtained from the database in Duke.
     */
    public Deadline(String[] data) {
        super(data[2]);
        this.setCompleted(Objects.equals(data[1], "X"));
        this.deadline = LocalDateTime.parse(data[3]);
    }

    /**
     * @return the status of the Deadline task with its time formatted.
     */
    @Override
    public String status() {

        String status = this.isCompleted() ? "[X] " : "[ ] ";
        return "[" + TYPE_TO_STRING + "]" + status + this.getDetails() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " ["
                + this.deadline.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")";
    }

    /**
     * @return all relevant information of the Deadline task in an ArrayList of Strings to be saved into the Database.
     */
    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(TYPE_TO_STRING);
        data.add(this.isCompleted() ? "X" : " ");
        data.add(this.getDetails());
        data.add(this.deadline.toString());
        return data;
    }
}
