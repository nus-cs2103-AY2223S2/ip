import java.util.regex.PatternSyntaxException;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd.split(" /by")[0],
                cmd.split(" /by")[1],
                false);
    }
    public Deadline(String cmd, boolean isDone) {
        this(cmd.split(" /by")[0],
                cmd.split(" /by")[1],
                isDone);
    }
    public Deadline(String task, String deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;
    }
    /**
     * Return the String for Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), deadline);
    }
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("D | %d | %s | %s", done, getTask(), deadline);
    }
}