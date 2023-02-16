package bob;

/** Todo is the most basic type of Task, requiring only a task description */
public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo t = (Todo) o;
            return this.description.equals(t.description);
        }
        return false;
    }
}
