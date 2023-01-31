package duke;

public class Parser {
    public static String[] parseCommand(String userInput) {
        return userInput.split("\\s", 2);
    }
}
