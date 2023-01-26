package duke.dukeexception;

import java.lang.StringBuilder;

/**
 * Class to manage problems during execution of Duke CLI
 */
public class DukeException extends Exception{
    private static final String STARTING = "    ____________________________________________________________\n";
    private static final String ENDING = "    ____________________________________________________________\n";
    private static final String SPACING = "     ☹ OOPS!!! ";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeException.STARTING).append(DukeException.SPACING).append(super.getMessage()).append("\n");
        sb.append(DukeException.ENDING);
        return sb.toString();
    }
}
