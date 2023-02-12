package wessy.exceptions.numofinput;

import wessy.exceptions.WessyException;

public class MissingInputException extends WessyException {
    private static final String ENDING = " is missing.";
    private final String cmd;

    public MissingInputException(String cmd) {
        super((cmd.equals("mark") || cmd.equals("unmark"))
                ? "The chosen task number of the '"
                : "The task description of the '");
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        String addStr = "'";
        if (cmd.equals("mark") || cmd.equals("unmark")) {
            addStr += " command";
        }
        return super.toString() + cmd + addStr + ENDING;
    }
}
