package duke.parser;

import duke.exception.WrongFormatException;
import duke.ui.Ui;

public class HandleBye {
    public HandleBye() {
    }

    public static String performBye(String input, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.equals("bye");

        if (!correctFormat) {
            throw new WrongFormatException("bye");
        }

        return ui.sayBye();
    }
}
