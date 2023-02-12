package wessy.exceptions.numofinput;

import wessy.exceptions.WessyException;

/**
 * MissingInputException is an exception that should be thrown when the task
 * description is missing for the "event", "deadline" or "todo" commands, or
 * when the task number is missing for the "mark", "unmark" or "delete"
 * commands.
 */
public class MissingInputException extends WessyException {
    private static final String ENDING = " is missing.";
    private final String cmd;

    /**
     * Constructs an instance of MissingInputException.
     *
     * @param cmd The command requested by the user, in its String form.
     */
    public MissingInputException(String cmd) {
        super((cmd.equals("mark") || cmd.equals("unmark"))
                ? "The chosen task number of the '"
                : "The task description of the '");
        this.cmd = cmd;
    }

    /** Overrides the toString() method of the parent class, WessyException.
     * Returns the String representation of this exception, by adding more
     * details to the result returned by the parent's method.
     *
     * @return The String representation of this exception.
     */
    @Override
    public String toString() {
        String addStr = "'";
        if (cmd.equals("mark") || cmd.equals("unmark")) {
            addStr += " command";
        }
        return super.toString() + cmd + addStr + ENDING;
    }
}
