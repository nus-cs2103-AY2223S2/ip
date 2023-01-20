public class ToDo extends Task {

    public ToDo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Crapadoodle! the description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        // [T][X] read book
        return "[T]" + " " + super.getStatusIcon() + " " + super.toString();
    }
}
