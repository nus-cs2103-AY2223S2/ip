package DukeHelpfulCode.Commands;

import java.util.Locale;

public class MarkCommandParser {

    public static Command parse(String[] userInput) {
        boolean isMark = true;
        int index;

        try {
            if (userInput[0].toLowerCase(Locale.ROOT).equals("unmark")) {
                isMark = false;
            }
            index = Integer.parseInt(userInput[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Sorry, I don't understand.");
        }
        // index = Integer.parseInt(userInput[1]);

        return new MarkCommand(isMark, index);
    }
}
