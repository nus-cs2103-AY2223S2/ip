
package Duke.Exceptions;
import Duke.Ui;
/**
 * Contains a duke exception object when description is missing for the command.
 */

public class EmptyDescriptionException extends DukeException {
    private String command;
    /**
     * Constructor for the emptyDescription Exception
     * @param command user inputted which require description as arugment
     */

    public EmptyDescriptionException(String command) {
        this.command = command;
    }
    /**
     * Obtain the string representation of the exception.
     * @return string representation of the exception
     */

    @Override
    public String toString() {
        return  String.format("%s The description of a %s cannot be empty.\n" + Ui.Underline(), super.toString(), command );
    }
}
