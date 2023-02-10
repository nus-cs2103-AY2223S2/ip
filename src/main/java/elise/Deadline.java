package elise;

/**
 * Represent a task with a deadline.
 */
class Deadline extends Task {
    private MaybeDate end;

    /**
     * Constructor for Deadline.
     *
     * @param status Completed or not.
     * @param content Contains message and end date.
     */
    protected Deadline(boolean status, String[] content) {
        super(status, content[0]);
        this.end = Parser.parseDate(content[1]);
    }

    /**
     * Returns the type icon dedicated to the deadline task.
     *
     * @return Type icon of deadline task
     */
    @Override
    protected String getTypeIcon() {
        return "D";
    }

    /**
     * Returns string representation of task.
     *
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + end + ")";
    }

    /**
     * Returns string representation of task to be stored in file.
     *
     * @return String representation of deadline task to be stored in file.
     */
    @Override
    protected String fileMessage() {
        return String.format("%s||%d||%s||%s\n", getTypeIcon(), isDone ? 1 : 0, content, end);
    }
}
