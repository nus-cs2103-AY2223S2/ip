package duke.parser;

import duke.exception.WrongFormatException;
import duke.ui.Ui;

/**
 * Processes and handles bye command from user
 */
public class HandleBye {
    public HandleBye() {
    }

    /**
     * Check the format of input and perform Bye command
     * @param input Entered by user
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui
     * @throws WrongFormatException This exception is thrown when input contains more words than "bye"
     */
    public static String performBye(String input, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.trim().equals("bye");

        if (!correctFormat) {
            throw new WrongFormatException("bye");
        }

        return ui.sayBye();
    }
}
