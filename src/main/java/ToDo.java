public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        // [T][X] read book
        return "[T]" + " " + super.getStatusIcon() + " " + super.toString();
    }
}
