package duke;

/**
 * Todo is a task that inherits Tasks, it represents a task that
 * you are supposed to complete
 */
public class Todo extends Tasks {

    /**
     * Returns a Todo object that inherits the Tasks Class it is a certain
     * task that is to be completed and it is first set to be undone
     * @param description the details of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString() method, it prints out the full description
     * of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Saves the todo task into data/duke.txt
     */
    @Override
    public String log() {
        return "T" + super.log() + "\n";
    }
}
