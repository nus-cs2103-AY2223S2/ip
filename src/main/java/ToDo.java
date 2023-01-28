public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCommand() {
        return "todo " + super.description + (super.isDone ? "\nmark last": "");
    }
}
