package duke.exceptions;

import duke.functions.Ui;

/**
 * The exception class that indicates that the command entered is incorrect.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class InvalidCommandException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public InvalidCommandException() {
        super("Sorry, I don't know what that line means. You could try typing from our list of commands:\n"
                + Ui.commandList.toString());
    }
}
