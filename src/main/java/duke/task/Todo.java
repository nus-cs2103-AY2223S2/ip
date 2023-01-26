package duke.task;

import java.util.Arrays;
import java.util.HashSet;

public class Todo extends Task {
    protected HashSet<String> wordsInDescription = new HashSet<>();

    public Todo(String description) {
        super(description);
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
    
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
