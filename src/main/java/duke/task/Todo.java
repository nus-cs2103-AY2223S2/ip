package duke.task;

/**
 * Class for Todo object.
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */
public class Todo extends Task {

    /**
     * Constructor of todo task
     * @param description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Encode task into String for easier decode when tasks from loading duke.txt
     * @return String format of task
     */
    @Override
    public String encode() {
        return "todo"
                + " " + this.isDone
                + " " + this.getPriority()
                + " " + this.description;
    }

    /**
     * Convert task into String for display in taskList
     * @return String of task type, status and task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
