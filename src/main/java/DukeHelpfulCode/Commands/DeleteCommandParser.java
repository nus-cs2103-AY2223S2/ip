package DukeHelpfulCode.Commands;

public class DeleteCommandParser {

    public static Command parse(String[] userInput) {
        int index;

        try {
            index = Integer.parseInt(userInput[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Sorry, I don't understand.");
        }
        index = Integer.parseInt(userInput[1]);

        return new DeleteCommand(index);
    }
}
