package jeo.parser;

import static jeo.parser.JeoParser.FROM_PREFIX;
import static jeo.parser.JeoParser.TAG_PREFIX;
import static jeo.parser.JeoParser.TO_PREFIX;

import java.util.ArrayList;
import java.util.Arrays;

import jeo.command.Command;
import jeo.command.EventCommand;
import jeo.exception.JeoException;
import jeo.task.Event;

/**
 * Parses input arguments and creates an EventCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class EventParser implements Parser {

    /**
     * Parses the given input arguments and returns an EventCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return EventCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        String description = "";
        String from = "";
        int splitInputLength = splitInput.length;
        if (splitInputLength == 1 || splitInput[1].equals(FROM_PREFIX)
                || splitInput[1].equals(TO_PREFIX) || splitInput[1].equals(TAG_PREFIX)) {
            throw new JeoException("Please enter a task description after event.", "event");
        }
        if (!Arrays.asList(splitInput).contains(FROM_PREFIX)) {
            throw new JeoException("Missing the /from prefix!", "event");
        }
        int indexOfFromPrefix = 0;
        for (int i = 1; i < splitInputLength; i++) {
            boolean argIsFromPrefix = splitInput[i].equals(FROM_PREFIX);
            if (argIsFromPrefix) {
                description = ParserUtil.parseSubArrayString(splitInput, 1, i);
                indexOfFromPrefix = i;
                break;
            }
        }
        if (indexOfFromPrefix + 1 == splitInputLength || indexOfFromPrefix + 2 == splitInputLength) {
            throw new JeoException("Missing valid date after the prefix /from", "event");
        }
        from = splitInput[indexOfFromPrefix + 1] + " " + splitInput[indexOfFromPrefix + 2];
        if (indexOfFromPrefix + 3 == splitInputLength) {
            throw new JeoException("Missing the prefix /to", "event");
        }
        if (!splitInput[indexOfFromPrefix + 3].equals(TO_PREFIX)) {
            throw new JeoException("Invalid command format!", "event");
        }
        return parseTo(splitInput, indexOfFromPrefix + 3, description, from);
    }

    /**
     * Parses the given input arguments starting from the /to prefix up till tags
     * and returns the resultant EventCommand object for execution.
     * @param splitInput user input split by white spaces
     * @param indexOfToPrefix index position of /to prefix
     * @param description parsed description
     * @param from parsed from date
     * @return EventCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    public Command parseTo(String[] splitInput, int indexOfToPrefix, String description, String from)
            throws JeoException {
        String to = "";
        String tagsString = "";
        int splitInputLength = splitInput.length;
        if (indexOfToPrefix + 1 == splitInputLength || indexOfToPrefix + 2 == splitInputLength) {
            throw new JeoException("Missing valid date after prefix /to", "event");
        }
        to = splitInput[indexOfToPrefix + 1] + " " + splitInput[indexOfToPrefix + 2];
        if (indexOfToPrefix + 3 != splitInputLength && splitInput[indexOfToPrefix + 3].equals(TAG_PREFIX)) {
            tagsString = String.join("\\", ParserUtil.parseTags(
                    splitInput, indexOfToPrefix + 3, "event"));
        } else if (indexOfToPrefix + 3 != splitInputLength) {
            throw new JeoException("Invalid command format!", "event");
        } else {
            tagsString = String.join("\\", new ArrayList<>());
        }
        return new EventCommand(new Event(description, from, to, tagsString));
    }
}
