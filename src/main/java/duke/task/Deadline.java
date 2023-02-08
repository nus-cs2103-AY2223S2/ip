package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * A subclass of Task that represents
 * a task with a deadline.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */


public class Deadline extends Task {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd"
            + " HHmm");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM"
            + " yyyy h:mm a");
    protected LocalDateTime endTime;
    protected String endTimeString;
    protected HashSet<String> wordsInDescription = new HashSet<>();

    /**
     * Constructor of Deadline.
     *
     * @param description Description of the task.
     * @param endTime Time limit of the task.
     */
    public Deadline(String description, String endTime) throws DateTimeParseException {
        super(description);
        assert !description.equals("");
        this.endTimeString = endTime;
        this.endTime = LocalDateTime.parse(endTime, INPUT_DATE_FORMAT);
        this.wordsInDescription.addAll(Arrays.asList(description.split(" ")));
    }

    /**
     * Method to check if the description of
     * the task contains the word that the user
     * is searching for.
     * @param word Search term of Find
     * @return True if the word exists in the description.
     */
    public boolean hasWord(String word) {
        return this.wordsInDescription.contains(word);
    }

    /**
     * Returns a string representation of Deadline.
     * @return String.
     */
    public String toString() {
        return "[D]" + getStatusIcon()
                + " " + description + " (by: "
                + endTime.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Method that returns String representation
     * of deadline in txt file.
     * @return String representation of Deadline in txt file.
     */
    public String toStorage() {
        // deadline read book /by ????
        return "deadline#"
                + description + "#"
                + "/by#"
                + this.endTimeString + "#"
                + getStatusIcon();
    }
}
