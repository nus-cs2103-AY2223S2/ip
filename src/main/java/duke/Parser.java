package duke;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDataFileException;
import exceptions.UnknownCommandException;
import tasks.Task;

/**
 * Parses user commands and commands inputted through the data file stored in memory.
 */
public class Parser {
    /**
     * Returns the input corresponding to the input required to request to close the application.
     *
     * @return The input that will close the application.
     */
    public static String getByeInput() {
        String byeInput = "bye";
        return byeInput;
    }

    /**
     * Parses a string input by the user into an array of each segment of the command.
     *
     * @param toParse String input by the user.
     * @return Array of each segment of the command.
     * @throws DukeException if the command given is invalid or not a command known by the chatbot.
     */
    public static String[] parseCommand(String toParse) throws DukeException {
        if (toParse.equals(Parser.getByeInput())) {
            return new String[] {"bye"};
        } else if (toParse.equals("list")) {
            return new String[] {"list"};
        } else if (toParse.startsWith("mark")) {
            Matcher matcher = compileAndMatch("mark (.*)", toParse);
            checkForMatch(matcher, 1, "The task number of a mark command cannot be empty.");
            return new String[] {"mark", matcher.group(1)};
        } else if (toParse.startsWith("unmark")) {
            Matcher matcher = compileAndMatch("unmark (.*)", toParse);
            checkForMatch(matcher, 1, "The task number of an unmark command cannot be empty.");
            return new String[] {"unmark", matcher.group(1)};
        } else if (toParse.startsWith("delete")) {
            Matcher matcher = compileAndMatch("delete (.*)", toParse);
            checkForMatch(matcher, 1, "The task number to be deleted must be specified, and must be an integer.");
            return new String[] {"delete", matcher.group(1)};
        } else if (toParse.startsWith("find")) {
            Matcher matcher = compileAndMatch("find (.*)", toParse);
            checkForMatch(matcher, 1, "Must supply a search string to the find command.");
            return new String[] {"find", matcher.group(1)};
        } else if (toParse.startsWith("todo")) {
            Matcher matcher = compileAndMatch("todo (.*) /prio (\\d+)", toParse);
            checkForMatch(matcher, 2,
                "The description of a todo cannot be empty, and the priority must be a positive integer.");
            return new String[] {"todo", matcher.group(1), matcher.group(2)};
        } else if (toParse.startsWith("deadline")) {
            Matcher matcher = compileAndMatch("deadline (.*) /by (.*) /prio (\\d+)", toParse);
            checkForMatch(matcher, 3,
                "The end date of a deadline cannot be empty, and the priority must be a positive integer.");
            return new String[] {"deadline", matcher.group(1), matcher.group(3), matcher.group(2)};
        } else if (toParse.startsWith("event")) {
            Matcher matcher = compileAndMatch("event (.*) /from (.*) /to (.*) /prio (\\d+)", toParse);
            checkForMatch(matcher, 4,
                "An event must have a nonempty from date and to date, and the priority must be a positive integer.");
            return new String[] {"event", matcher.group(1), matcher.group(4), matcher.group(2), matcher.group(3)};
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Checks if the given matcher found a match with the correct number of groups.
     *
     * @param matcher            Matcher containing the regex and the string to be matched with.
     * @param expectedGroupCount Number of groups that should be matched.
     * @param errorMessage       Description of the error that is indicated by a failed match.
     * @throws DukeException if the actual number of groups matched is not the same as expected.
     */
    private static void checkForMatch(Matcher matcher, int expectedGroupCount, String errorMessage)
            throws DukeException {
        if (!matcher.find()) {
            throw new InvalidCommandException(errorMessage);
        }

        try {
            // Ignore the first group which is the entire matched string
            for (int i = 1; i < expectedGroupCount + 1; i++) {
                if (matcher.group(i).length() == 0) {
                    throw new InvalidCommandException(errorMessage);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(errorMessage);
        }
    }

    /**
     * Parses a string input from the data file into an array of each segment of the command.
     *
     * @param toParse String input from the data file.
     * @return Array of each segment of the command.
     * @throws DukeException if the command given is invalid or is not a command known by the chatbot.
     */
    public static String[] parseFileCommand(String toParse) throws DukeException {
        // Groups: 1: TaskType, 2: isDone, 3: priority, 4: task details
        Pattern fileCommandPattern = Pattern.compile("\\d+\\.\\[([TDE])]\\[([ X])\\]\\[(\\d+)\\] (.*)");
        Pattern deadlinePattern = Pattern.compile("(.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("(.*) /from (.*) /to (.*)");
        Matcher fileCommandMatcher = fileCommandPattern.matcher(toParse);
        Matcher individualMatcher;
        if (!fileCommandMatcher.find()) {
            throw new InvalidDataFileException();
        }
        switch (fileCommandMatcher.group(1)) {
        case "T":
            return new String[] {"T", fileCommandMatcher.group(2), fileCommandMatcher.group(3),
                fileCommandMatcher.group(4)};
        case "D":
            individualMatcher = deadlinePattern.matcher(fileCommandMatcher.group(4));
            if (individualMatcher.find()) {
                return new String[] {"D", fileCommandMatcher.group(2), fileCommandMatcher.group(3),
                    individualMatcher.group(1), individualMatcher.group(2)};
            }
            // Fallthrough
        case "E":
            individualMatcher = eventPattern.matcher(fileCommandMatcher.group(4));
            if (individualMatcher.find()) {
                return new String[] {"D", fileCommandMatcher.group(2), fileCommandMatcher.group(3),
                    individualMatcher.group(1), individualMatcher.group(2), individualMatcher.group(3)};
            }
            // Fallthrough
        default:
            throw new InvalidDataFileException();
        }
    }

    /**
     * Parses a given date time string into a LocalDateTime object.
     *
     * @param dateTime Date time string.
     * @param fromFile Whether the date time string was input via command line or from the data file.
     * @return LocalDateTime object representing the date time given.
     */
    public static LocalDateTime parseDate(String dateTime, boolean fromFile) {
        if (fromFile) {
            return LocalDateTime.parse(dateTime, Task.getOutputDateTimeFormatter());
        } else {
            return LocalDateTime.parse(dateTime, Task.getInputDateTimeFormatter());
        }
    }

    /**
     * Compiles a given regex and matches it with the given input.
     *
     * @param regex   Regular expression search pattern.
     * @param toMatch String to match regex with.
     * @return Matcher object containing the given regex and input string.
     */
    private static Matcher compileAndMatch(String regex, String toMatch) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toMatch);
    }
}
