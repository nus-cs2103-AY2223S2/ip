package duke;

/**
 * A task type that the chatting bot can create.
 */
public class Todos extends Task {

    /**
     * The constructor of this class.
     *
     * @param name
     */
    public Todos(String name) {
        super(name);
    }

    /**
     * The toString method.
     *
     * @return the task name with the status.
     */
    public String toString() {
        if (this.getStatus()) {
            return "[T][X] " + this.getName();
        } else {
            return "[T][ ] " + this.getName();
        }
    }

}
