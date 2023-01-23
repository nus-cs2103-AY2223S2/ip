public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFormatString() {
        return "T | " + (super.done ? "1" : "0") + " | " + super.name;
    }
}
