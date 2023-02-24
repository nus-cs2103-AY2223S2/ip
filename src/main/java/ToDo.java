import java.util.regex.PatternSyntaxException;

public class ToDo extends Task {
    public ToDo(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        super(cmd);
    }
    public ToDo(String cmd, boolean isDone) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        super(cmd, isDone);
    }
    /**
     * Return the String for ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("T | %d | %s", done, getTask());
    }
}
