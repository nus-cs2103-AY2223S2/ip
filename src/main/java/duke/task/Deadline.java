package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.HashSet;

public class Deadline extends Task {
    protected HashSet<String> wordsInDescription = new HashSet<>();
    protected LocalDateTime by;
    protected DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.wordsInDescription.addAll(Arrays.asList(description.split(" ")));
        this.by = LocalDateTime.parse(by, INPUT_DATE_FORMAT);
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
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
