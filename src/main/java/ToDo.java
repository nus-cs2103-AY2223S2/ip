public class ToDo extends Task {

    public ToDo(String description, String by) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}