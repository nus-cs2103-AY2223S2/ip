public class Todo extends Task {
    public Todo(String name) {
        super(name, null, null);
    }

    @Override
    public String getFileDesc() {
        return this.isDone
        ? "T|1|" + this.name
        : "T|0|" + this.name;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[T][X] " + this.name
                : "[T][ ] " + this.name;
    }
}