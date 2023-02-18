package task;

/**
 * Todo is a subclass of Task that adds its own implementation for the toSaveFormat() and toString() methods.
 */
public class Todo extends Task {
    static final String TASKSYMBOL = "[T]";
    static final String TASKSAVESYMBOL = "T";


    /**
     * Constructor to create a new Todo instance with the specified name.
     * @param name the name of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the Todo task in the format
     * "[TASKSAVESYMBOL | [mark] | [tag] | [task name] | [tag name]" where
     * TASKSAVESYMBOL is a static final variable that represents a Todo task for saving
     * mark is "1" if the task is marked done, and "0" otherwise.
     * tag is "1" if the task is tagged, and "0" otherwise. If tag is 0, there is no tag name.
     *
     * @return a formatted string for todo task that can be saved to a file
     */
    @Override
    public String toSaveFormat() {
        return TASKSAVESYMBOL + super.toSaveFormat() + getSaveTag();
    }

    /**
     * Returns the Todo task as a string in the format "[TASKSYMBOL][status icon][task name][tag name]" where
     * TASKSYMBOL is a static final variable that represents a Todo task to display to user
     * status icon is "X" if the task is marked done, and " " otherwise.
     * tag is "1" if the task is tagged, and "0" otherwise. If tag is 0, there is no tag name.
     *
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return TASKSYMBOL + super.toString();
    }
}
