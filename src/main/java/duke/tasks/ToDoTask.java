package duke.tasks;

/**
 * Represents a task that a user has to do.
 */
public class ToDoTask extends Task {

    public ToDoTask(String title) {
        super(title);
    }

    /**
     * Returns a String that contains information
     * on the ToDoTask object that is used for loading
     * the task into the ToDoList on Duke startup.
     *
     * @return A String that is used for loading the task into Duke on startup.
     */
    @Override
    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        return "TODO/+/" + status + this.title + "\n";
    }

    /**
     * Returns an empty String as ToDoTask objects do not have a date to be compared to.
     *
     * @return An empty String.
     */
    @Override
    public String remind(int dayRange) {
        return "";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[T]" + status + this.title;
    }
}
