import java.util.regex.PatternSyntaxException;

public class Event extends Task {
    private String from;
    private String to;
    public Event(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd.split(" /")[0],
                cmd.split(" /")[1].replace("from ", ""),
                cmd.split(" /")[2].replace("to ", ""),
                false);
    }
    public Event(String cmd, boolean isDone) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd.split(" /")[0],
                cmd.split(" /")[1].replace("from ", ""),
                cmd.split(" /")[2].replace("to ", ""),
                isDone);
    }
    public Event(String task, String from, String to, boolean isDone) {
        super(task, isDone);
        this.from = from;
        this.to = to;
    }
    /**
     * Return the String for Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("E | %d | %s | %s-%s", done, getTask(), from, to);
    }
}
