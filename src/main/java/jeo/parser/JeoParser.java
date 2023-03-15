package jeo.parser;

import java.util.HashMap;

import jeo.command.ByeCommand;
import jeo.command.Command;
import jeo.command.CommandType;
import jeo.command.ListCommand;
import jeo.exception.JeoException;

/**
 * Represents the parser which parses user input as a string.
 * @author Goh Jun How
 * @version 0.3
 */
public class JeoParser {
    public static final String TAG_PREFIX = "/tag";
    public static final String BY_PREFIX = "/by";
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";

    /**
     * Accepts a string that represents the raw string input to be parsed as command and action.
     * @param input String representing the raw string input of current line.
     * @return HashMap linking each essential aspect of the input to its corresponding substring.
     * @throws JeoException for custom errors.
     */
    public static Command parseString(String input) throws JeoException {
        // To prevent interference with saving format
        if (input.contains("\\")) {
            throw new JeoException("Backslash character \"\\\" not allowed", "bye");
        }
        if (input.trim().isEmpty()) {
            throw new JeoException("Input cannot be empty!", "bye");
        }
        String[] splitInput = input.split("\\s+");
        CommandType command = CommandType.valueOf(splitInput[0].toUpperCase());
        switch (command) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            return new MarkParser().parse(splitInput);

        case UNMARK:
            return new UnmarkParser().parse(splitInput);

        case DELETE:
            return new DeleteParser().parse(splitInput);

        case TODO:
            return new TodoParser().parse(splitInput);

        case DEADLINE:
            return new DeadlineParser().parse(splitInput);

        case EVENT:
            return new EventParser().parse(splitInput);

        case DUE:
            return new DueParser().parse(splitInput);

        case FIND:
            return new FindParser().parse(splitInput);

        default:
            throw new IllegalStateException();
        }
    }
}
