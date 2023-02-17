package duke.task;

/**
 * Represent a Todo class
 * Handles with todo commands
 */
public class Todo extends Task {

    /**
     * Initializes a new Todo object
     * @param type of task
     * @param detail of Todo
     * @param marked wheter Todo is marked or not
     */
    public Todo(String type, String detail, boolean marked) {
        super(type, detail, marked);
    }

    public Todo(String type, String detail) {
        super(type, detail);
    }


    /**
     * Returns todo printed out properly.
     *
     * @return todo in full details.
     */
    @Override
    public String toString() {
        if (marked) {
            return "[T][X] " + super.detail;
        } else {
            return "[T][ ] " + super.detail;
        }
    }
}
