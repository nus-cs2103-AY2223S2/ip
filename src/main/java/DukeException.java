import java.lang.StringBuilder;
public class DukeException extends Exception{
    private static final String starting = "    ____________________________________________________________\n";
    private static final String ending = "    ____________________________________________________________\n";
    private static final String spacing = "     ☹ OOPS!!! ";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeException.starting).append(DukeException.spacing).append(super.getMessage()).append("\n");
        sb.append(DukeException.ending);
        return sb.toString();
    }
}
