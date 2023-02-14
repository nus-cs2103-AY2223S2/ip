package duke.parser;

import duke.exception.WrongFormatException;

public class HandleBye {
    public HandleBye() {
    }

    public static String performBye(String input) throws WrongFormatException {
        boolean correctFormat = input.equals("bye");

        if (!correctFormat) {
            throw new WrongFormatException("bye");
        }

        return "Bye. Hope to see you again soon!";
    }
}
