package colette.task;

/** Class that represents a todo */
public class Todo extends Task {

    /**
     * Constructs a Todo object with
     * the given name.
     *
     * @param name Name of this todo.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getFileRepresentation() {
        return "T" + "@" + (super.isDone() ? "1" : "0") + "@" + this.getName();
    }

}
