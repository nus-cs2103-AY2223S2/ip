package duke.task;

/**
 * Stores a todo object.
 * A todo is a task that does not have a deadline.
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return super.compareTo(o);
        }
        return super.compareTo(o);
    }
}
