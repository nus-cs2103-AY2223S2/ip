public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        this.by = by;
        if (by.isBlank() || description.isBlank()) {
            throw new DukeException(" ☹ OOPS!!! The description or time/date of a deadline cannot be empty.\n");
        }
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}