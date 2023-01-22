public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
