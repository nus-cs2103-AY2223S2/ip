import java.util.regex.PatternSyntaxException;

public class ToDo extends Task {
    public ToDo(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        super(cmd);
    }
    /**
     * Return the String for ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
