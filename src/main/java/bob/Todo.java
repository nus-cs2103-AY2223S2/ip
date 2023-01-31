package bob;

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
            return t.description.equals(this.description);
        }
        return false;
    }
}
