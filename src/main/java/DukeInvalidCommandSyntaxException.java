/**
 * DukeInvalidCommandSyntaxException indicates that a user-supplied command has an invalid syntax
 * and hence cannot be parsed. Note that even if a command has a valid syntax, it does not mean
 * that the command is a valid command, because it may not correspond to any known commands or
 * has the wrong options.
 * All commands must have the syntax "main-command [/option option-args | args...]"
 */
public class DukeInvalidCommandSyntaxException extends DukeException {
    public DukeInvalidCommandSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
