package duke;

/**
 * A task type that the chatting bot can create.
 */
public class Todos extends Task {
    public Todos(String name) {
        super(name);
    }

    /**
     * Returns the toString.
     *
     * @return the task list
     */
    public String toString() {
        if (this.getStatus()) {
            return "[T][X] " + this.getName();
        } else {
            return "[T][ ] " + this.getName();
        }
    }

}
