package duke;

public class Parser {
    public enum ParseFunctions {
        SPLIT_ALL, TODO, DEADLINE, EVENT
    }

    /**
     * Parses user input.
     * @param input String that user enters
     * @param parse_type Type of command
     * @return List of strings
     * @throws EmptyDescriptionException
     */
    public static String[] parse(String input, ParseFunctions parse_type) throws EmptyDescriptionException {
        switch (parse_type) {
        case SPLIT_ALL:
            return input.split(" ");
        case TODO:
            String[] parsed = input.split(" ", 2);
            if (parsed.length < 2) {
                throw new EmptyDescriptionException("Add an argument");
            }
            return parsed; // split into 2
        case DEADLINE:
            String[] otherArgs = input.split(" ", 2);
            String[] taskAndTime = otherArgs[1].split(" /by ", 2);
            return new String[]{otherArgs[0], taskAndTime[0], taskAndTime[1]};
        case EVENT:
            otherArgs = input.split(" ", 2);
            taskAndTime = otherArgs[1].split(" /from ", 2);
            String[] toTime = taskAndTime[1].split(" /to ", 2);
            return new String[]{otherArgs[0], taskAndTime[0], toTime[0], toTime[1]};
        default:
            throw new java.lang.Error("Will not reach here");
        }
    }
}
