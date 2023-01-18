public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) throws DukeException {
        super(description.trim());
        this.by = by.trim();
        if (this.description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (this.by.equals("")) {
            throw new DukeException("The 'by' date of a deadline cannot be empty.");
        }
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
