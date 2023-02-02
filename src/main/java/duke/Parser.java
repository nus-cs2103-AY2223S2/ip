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
     * Get the input corresponding to the input required to request to close the application.
     * @return The input that will close the application.
     */
    public static String getByeInput() {
        return "bye";
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
            if (matcher.find() && matcher.group(1).length() > 0) {
                return new String[] {"mark", matcher.group(1)};
            } else {
                throw new InvalidCommandException("The task number of a mark command cannot be empty.");
            }
        } else if (toParse.startsWith("unmark")) {
            Matcher matcher = compileAndMatch("unmark (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0) {
                return new String[] {"unmark", matcher.group(1)};
            } else {
                throw new InvalidCommandException("The task number of an unmark command cannot be empty.");
            }
        } else if (toParse.startsWith("delete")) {
            Matcher matcher = compileAndMatch("delete (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0) {
                return new String[] {"delete", matcher.group(1)};
            } else {
                throw new InvalidCommandException(
                    "The task number to be deleted must be specified, and must be an integer.");
            }
        } else if (toParse.startsWith("find")) {
            Matcher matcher = compileAndMatch("find (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0) {
                return new String[] {"find", matcher.group(1)};
            } else {
                throw new InvalidCommandException("Must supply a search string to the find command.");
            }
        } else if (toParse.startsWith("todo")) {
            Matcher matcher = compileAndMatch("todo (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0) {
                return new String[] {"todo", matcher.group(1)};
            } else {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
        } else if (toParse.startsWith("deadline")) {
            Matcher matcher = compileAndMatch("deadline (.*) /by (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0) {
                return new String[] {"deadline", matcher.group(1), matcher.group(2)};
            } else {
                throw new InvalidCommandException("The end date of a deadline cannot be empty.");
            }
        } else if (toParse.startsWith("event")) {
            Matcher matcher = compileAndMatch("event (.*) /from (.*) /to (.*)", toParse);
            if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0
                && matcher.group(3).length() > 0) {
                return new String[] {"event", matcher.group(1), matcher.group(2), matcher.group(3)};
            } else {
                throw new InvalidCommandException("An event must have a nonempty from date and a to date.");
            }
        } else {
            throw new UnknownCommandException();
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
        Pattern fileCommandPattern = Pattern.compile("\\d+\\.\\[([TDE])]\\[([ X])\\] (.*)");
        Pattern deadlinePattern = Pattern.compile("(.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("(.*) /from (.*) /to (.*)");
        Matcher fileCommandMatcher = fileCommandPattern.matcher(toParse);
        Matcher individualMatcher;
        if (fileCommandMatcher.find()) {
            switch (fileCommandMatcher.group(1)) {
            case "T":
                return new String[] {"T", fileCommandMatcher.group(2), fileCommandMatcher.group(3)};
            case "D":
                individualMatcher = deadlinePattern.matcher(fileCommandMatcher.group(3));
                if (individualMatcher.find()) {
                    return new String[] {"D", fileCommandMatcher.group(2), individualMatcher.group(1),
                        individualMatcher.group(2)};
                } else {
                    throw new InvalidDataFileException();
                }
            case "E":
                individualMatcher = eventPattern.matcher(fileCommandMatcher.group(3));
                if (individualMatcher.find()) {
                    return new String[] {"D", fileCommandMatcher.group(2), individualMatcher.group(1),
                        individualMatcher.group(2), individualMatcher.group(3)};
                } else {
                    throw new InvalidDataFileException();
                }
            default:
                throw new InvalidDataFileException();
            }
        } else {
            return new String[] {};
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
