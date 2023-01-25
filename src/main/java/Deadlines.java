/**
 * Represent a task with a deadline.
 */
class Deadlines extends Task {
    private MaybeDate end;

    protected Deadlines(boolean status, String[] content) {
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
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + end + ")";
    }

    @Override
    protected String fileMessage() {
        return String.format("%s||%d||%s||%s\n", getTypeIcon(), isDone ? 1 : 0, content, end);
    }


}
