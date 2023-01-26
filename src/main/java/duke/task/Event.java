package duke.task;

import java.util.Arrays;
import java.util.HashSet;

public class Event extends Task {
    protected HashSet<String> wordsInDescription = new HashSet<>();
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.wordsInDescription.addAll(Arrays.asList(description.split(" ")));
        this.from = from;
        this.to = to;
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

    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}