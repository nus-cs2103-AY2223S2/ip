package berry.task;

import java.util.HashSet;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description, boolean isDone, HashSet<String> tags) {
        super(description, isDone, tags);
    }

    public Todo(String description, HashSet<String> tags) {
        super(description, tags);
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing a todo task to be saved into the file
     */
    @Override
    public String interpretTaskToText() {
        String output = "T | " + this.getStatusIcon() + " | " + this.description;
        if (this.tags == null) {
            return output;
        }

        output += " |t";
        for (String tag : this.tags) {
            if (!tag.isBlank()) {
                output += " #" + tag;
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "[T]" + super.toString();
        if (this.tags == null) {
            return output;
        }

        for (String tag : this.tags) {
            if (!tag.isBlank()) {
                output += " #" + tag;
            }
        }
        return output;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo)
        {
            Todo newTodo = (Todo) object;
            if (this.description != newTodo.description) {
                return false;
            }
        }
        return true;
    }
}
