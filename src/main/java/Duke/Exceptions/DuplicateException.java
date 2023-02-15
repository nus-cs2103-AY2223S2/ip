package Duke.Exceptions;

import Duke.Ui;
/**
 * Contains a duke exception object when duplicate task is added
 */

public class DuplicateException extends DukeException {

    /**
     * Constructor for the emptyDescription Exception
     *
     */

    public DuplicateException() {
    }
    /**
     * Obtain the string representation of the exception.
     * @return string representation of the exception
     */

    @Override
    public String toString() {
        return  String.format("%s Task already present.\n" + Ui.Underline(), super.toString());
    }
}