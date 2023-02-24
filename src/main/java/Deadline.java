import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd,false);
    }
    public Deadline(String cmd, boolean isDone) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd.split(" /by ")[0],
                cmd.split(" /by ")[1],
                isDone);
    }
    public Deadline(String task, String deadlineString, boolean isDone)
            throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DateTimeParseException {
        super(task, isDone);
        this.deadline = LocalDateTime.parse(deadlineString);
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