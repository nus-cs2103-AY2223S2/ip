package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /** The summary status message to be displayed in Duke. */
    public String getStatus() {
        switch (this.size()) {
            case 0:
                return "Now you have no tasks in the list.";
            case 1:
                return "Now you have 1 task in the list.";
            default:
                return String.format("Now you have %d task in the list.", this.size());
        }
    }
    
    /**
     * Encodes this task list into a string.
     * @return The encoded task list.
     */
    public String encodeAsString() {
        return this.stream()
            .map(task -> task.encodeAsString())
            .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Nothing in the list.";
        }
        
        int listIndex = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : this) {
            output.append(listIndex + ". " + task.toString() + "\n");
            listIndex++;
        }
        // Removes trailing newline.
        output.setLength(output.length() - 1);
        return output.toString();
    }
}
