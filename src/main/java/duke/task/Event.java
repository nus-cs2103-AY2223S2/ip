package duke.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * A subclass of Task that represents
 * a task that is considered an event.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;
    protected HashSet<String> wordsInDescription = new HashSet<>();

    /**
     * Constructor of Event.
     *
     * @param description Description of the event.
     * @param startTime Start time of event.
     * @param endTime End time of event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        assert !description.equals("");
        this.startTime = startTime;
        this.endTime = endTime;
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
     * Returns a string representation of Event.
     * @return representation String representation of Event
     */
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description
                + " (from: "
                + startTime + " to: "
                + endTime + ")";
    }

    /**
     * Method that returns String representation
     * of event in txt file.
     * @return String representation of event in txt file.
     */
    public String toStorage() {
        return "event#"
                + description + "#"
                + "/from#" + startTime
                + "#/to#" + endTime
                + "#" + getStatusIcon();
    }
}
