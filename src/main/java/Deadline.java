/**
 * Represents a Deadline, which is a type of Task that has to be done before s specific date/time.
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description) throws DukeException {
        super(description.split("/by")[0]);
        try {
            this.deadline = description.split("/by")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return  String representation of a Deadline in this format: [type][<status>] <description> (by: <deadline>).
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s(by:%s)", this.getStatusIcon(), this.description, this.deadline);
    }
}
