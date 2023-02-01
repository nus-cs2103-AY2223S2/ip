package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;

/**
 * A simple parser for user commands.
 */
public class Parser {
    private List<String> parseByRegex(String regex, String command) {
        // https://www.tutorialspoint.com/javaregex/javaregex_capturing_groups.htm
        List<String> result = new ArrayList<>();
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);
        if (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                result.add(m.group(i));
            }
        }
        return result;
    }

    /**
     * Returns a stream of tokens from the user command.
     * @param userInput String to be parsed.
     * @return List of tokens corresponding to the command.
     * @throws DukeException If input does not correspond to any known commands.
     */
    public List<String> parseCommand(String userInput) throws DukeException {
        String command = userInput.trim();
        String[] tokens = command.split("\\s+", 2);
        List<String> result = new ArrayList<>();
        result.add(tokens[0]);

        switch(tokens[0]) {
        case "list":
            break;
        case "mark":
        case "unmark":
            result.addAll(parseByRegex("^\\s*(\\d+)\\s*$", tokens[1]));
            break;
        case "todo":
            result.addAll(parseByRegex("^\\s*([^/]+)\\s*$", tokens[1]));
            break;
        case "deadline":
            result.addAll(parseByRegex("^\\s*([^/]+)\\s+/by\\s+([^/]+)\\s*$", tokens[1]));
            break;
        case "event":
            result.addAll(parseByRegex("^\\s*([^/]+?)\\s+/from\\s+([^/]+?)\\s+"
                    + "/to\\s([^/]+)\\s*$", tokens[1]));
            break;
        case "delete":
            result.addAll(parseByRegex("^\\s+(\\d+?)\\s*$", tokens[1]));
            break;
        case "find":
            result.add(tokens[1].trim());
            break;
        default:
            throw new DukeUnknownCommandException("\tUnknown command\n");
        }
        return result;
    }
}
