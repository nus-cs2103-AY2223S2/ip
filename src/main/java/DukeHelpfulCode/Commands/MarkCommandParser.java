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
        } catch (NumberFormatException e) {
            return new ErrorCommand("Sorry, I don't understand.");
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Sorry, that index does not exist.");
        }
        if (index <= 0) {
            return new ErrorCommand("Sorry, that index does not exist.");
        }

        return new MarkCommand(isMark, index);
    }
}
