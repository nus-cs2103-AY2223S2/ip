package DukeHelpfulCode.Commands;

import java.util.Arrays;

public class FindCommandParser {

    public static Command parse(String[] userInput) {
        return new FindCommand(Arrays.copyOfRange(userInput,1,userInput.length));
    }
}
