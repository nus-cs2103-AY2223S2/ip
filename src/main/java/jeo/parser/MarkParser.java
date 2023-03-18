package jeo.parser;

import jeo.command.Command;
import jeo.command.MarkCommand;
import jeo.exception.JeoException;

/**
 * Parses input arguments and creates a MarkCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class MarkParser implements Parser {

    /**
     * Parses the given input arguments and returns a MarkCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return MarkCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "mark");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "mark") - 1;
        return new MarkCommand(index);
    }
}
