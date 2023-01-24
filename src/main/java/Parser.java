import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // Given a string, return an array [command, ...parameters]
    public static String[] parseCommand(String toParse) throws DukeException {
        if (toParse.equals("bye")) {
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
                throw new InvalidCommandException("The task number to be deleted must be specified, and must be an integer.");
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
            if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0 && matcher.group(3).length() > 0) {
                return new String[] {"event", matcher.group(1), matcher.group(2), matcher.group(3)};
            } else {
                throw new InvalidCommandException("An event must have a nonempty from date and a to date.");
            }
        } else {
            throw new UnknownCommandException();
        }
    }

    public static String[] parseFileCommand(String toParse) throws DukeException {
        Pattern fileCommandPattern = Pattern.compile("\\d+\\.\\[([TDE])]\\[([ X])\\] (.*)");
        Pattern deadlinePattern = Pattern.compile("(.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("(.*) /from (.*) /to (.*)");
        Matcher fileCommandMatcher, individualMatcher;
        fileCommandMatcher = fileCommandPattern.matcher(toParse);
        if (fileCommandMatcher.find()) {
            switch (fileCommandMatcher.group(1)) {
            case "T":
                return new String[]{"T", fileCommandMatcher.group(2), fileCommandMatcher.group(3)};
            case "D":
                individualMatcher = deadlinePattern.matcher(fileCommandMatcher.group(3));
                if (individualMatcher.find()) {
                    return new String[]{"D", fileCommandMatcher.group(2), individualMatcher.group(1), individualMatcher.group(2)};
                } else {
                    throw new InvalidDataFileException();
                }
            case "E":
                individualMatcher = eventPattern.matcher(fileCommandMatcher.group(3));
                if (individualMatcher.find()) {
                    return new String[]{"D", fileCommandMatcher.group(2), individualMatcher.group(1), individualMatcher.group(2), individualMatcher.group(3)};
                } else {
                    throw new InvalidDataFileException();
                }
            default:
                throw new InvalidDataFileException();
            }
        } else {
            return new String[]{};
        }
    }

    private static Matcher compileAndMatch(String regex, String toMatch) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toMatch);
    }
}
