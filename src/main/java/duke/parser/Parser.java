package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeUnknownCommandException;
import duke.exceptions.DukeWrongNumberOfArgumentsException;

/**
 * A simple parser for user commands.
 */
public class Parser {
    private List<String> parseByRegex(String regex, String command) {
        // Solution below adapted from https://www.tutorialspoint.com/javaregex/javaregex_capturing_groups.htm
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

    private List<String> splitStringIntoIntegers(String string) {
        String[] array = string.trim().split("\\s+");
        return Arrays.asList(array);
    }

    private void assertTokenLength(List<String> tokens, int length) throws DukeWrongNumberOfArgumentsException {
        if (tokens.size() != length) {
            throw new DukeWrongNumberOfArgumentsException("Wrong number of arguments supplied. This command requires "
                    + (length - 1) + " number of arguments.");
        }
    }

    private void assertHasArguments(String[] tokens) throws DukeInvalidArgumentException {
        if (tokens.length <= 1) {
            throw new DukeInvalidArgumentException("I urge you to supply at least one argument");
        }
    }

    private void assertIsNumber(String token) throws DukeInvalidArgumentException {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("The command requires integer arguments.");
        }
    }

    private void assertAllNumbers(List<String> tokenList, int start, int end) throws DukeInvalidArgumentException {
        try {
            for (int i = start; i <= end; i++) {
                Integer.parseInt(tokenList.get(i));
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("The command requires integer arguments.");
        }
    }
    private void assertIsDateString(String dateString) throws DukeInvalidArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        try {
            LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The command requires at least one argument in the format: "
                    + "yyyy-MM-dd-HH-mm. Example: 2023-02-02-02-09");
        }
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
        case "bye":
            break;
        case "mark":
        case "unmark":
            assertHasArguments(tokens);
            result.add(tokens[1].trim());
            assertTokenLength(result, 2);
            assertIsNumber(result.get(1));
            break;
        case "delete":
            assertHasArguments(tokens);
            result.addAll(splitStringIntoIntegers(tokens[1]));
            assertAllNumbers(result, 1, result.size() - 1);
            break;
        case "todo":
        case "find":
            assertHasArguments(tokens);
            result.add(tokens[1].trim());
            assertTokenLength(result, 2);
            break;
        case "deadline":
            assertHasArguments(tokens);
            result.addAll(parseByRegex("^\\s*([^/]+)\\s+/by\\s+([^/]+)\\s*$", tokens[1]));
            assertTokenLength(result, 3);
            assertIsDateString(result.get(2));
            break;
        case "event":
            assertHasArguments(tokens);
            result.addAll(parseByRegex("^\\s*([^/]+?)\\s+/from\\s+([^/]+?)\\s+/to\\s([^/]+)\\s*$", tokens[1]));
            assertTokenLength(result, 4);
            assertIsDateString(result.get(2));
            assertIsDateString(result.get(3));
            break;
        default:
            throw new DukeUnknownCommandException("I have no idea what you just said.");
        }
        return result;
    }
}
