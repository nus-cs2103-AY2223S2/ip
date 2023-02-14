package bob;

public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    public String toString() {
        return this.description;
    }
}
