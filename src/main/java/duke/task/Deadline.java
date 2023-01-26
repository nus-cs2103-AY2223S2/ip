package duke.task;

import java.util.Arrays;
import java.util.HashSet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A subclass of Task that represents
 * a task with a deadline.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */


public class Deadline extends Task {
    protected LocalDateTime endTime;
    protected HashSet<String> wordsInDescription = new HashSet<>();
    protected DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    /**
     * Constructor of Deadline.
     *
     * @param description Description of the task.
     * @param endTime Time limit of the task.
     */
    public Deadline(String description, String endTime) throws DateTimeParseException {
        super(description);
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
     * Returns a string representation of Deadline
     * @return String
     */
    public String toString() {
        return "[D]" + getStatusIcon()
                + " " + description + " (by: "
                + endTime.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
