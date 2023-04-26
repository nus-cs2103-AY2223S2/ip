package duke.exceptions;

import duke.ui.Ui;

/**
 *
 */
public class DukeException extends Exception {
    private static Ui ui;

    public DukeException(String message) {
        ui.showError(message);
    }

    public static void setUi(Ui dukeUi) {
        ui = dukeUi;
    }
}
