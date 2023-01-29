package dukes.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The exception class that handles all the run-time exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException class.
     *
     * @param str the message to be displayed.
     */
    public DukeException(String str) {
        super(str);
    }
}
