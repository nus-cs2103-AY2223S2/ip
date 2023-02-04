public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[T][X] " + this.name
                : "[T][ ] " + this.name;
    }
}