/**
 * Represents an event task.
 */
public class Events extends Task {
    private MaybeDate begin;
    private MaybeDate end;

    protected Events(boolean status, String[] content) {
        super(status, content[0]);
        this.begin = Parser.parseDate(content[1]);
        this.end = Parser.parseDate(content[2]);
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }

    /**
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + begin + " to: " + end + ")";
    }

    @Override
    protected String fileMessage() {
        return String.format("%s||%d||%s||%s||%s\n", getTypeIcon(), isDone ? 1 : 0, content, begin, end);
    }
}
