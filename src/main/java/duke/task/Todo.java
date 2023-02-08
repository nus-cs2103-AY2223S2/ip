package duke.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * A subclass of Task that represents
 * a todo task.
 *
 * @author  Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Todo extends Task {
    protected HashSet<String> wordsInDescription = new HashSet<>();

    /**
     * Constructor of the todo class.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
        assert !description.equals("");
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
     * Returns the string representation of a todo task.
     * @return String representation.
     */
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }

    /**
     * Method that returns String representation
     * of todo in txt file.
     * @return String representation of todo in txt file.
     */
    public String toStorage() {
        return "todo#"
                + description + "#"
                + getStatusIcon();
    }
}
