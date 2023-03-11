package roody.exceptions;

/**
 * A custom exception for incorrect inputs
 */
public class InputFormatException extends RoodyException {
    public static final String CMD = "I expected \"{Command}\" but got something else";
    public static final String CMD_INDEX = "I expected \"{Command} {Index}\" but got something else";
    public static final String CMD_KEY = "I expected \"{Command} {Keyword}\" but got something else";
    public static final String CMD_TODO = "I expected \"{Command} {Index}\" but got something else";
    public static final String CMD_DEADLINE = "I expected \"{Command} {Desc} /by {DateTime}\" but got something else";
    public static final String CMD_EVENT = "I expected \"{Command} {Desc} /from {DateTime} /to {DateTime}\" "
            + "but got something else";
    public InputFormatException(String s) {
        super(s);
    }
}
