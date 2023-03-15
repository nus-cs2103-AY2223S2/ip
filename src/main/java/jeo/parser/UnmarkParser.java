package jeo.parser;

import jeo.command.Command;
import jeo.command.UnmarkCommand;
import jeo.exception.JeoException;

/**
 * Parses input arguments and creates an UnmarkCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class UnmarkParser implements Parser {

    /**
     * Parses the given input arguments and returns an UnmarkCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return UnmarkCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "unmark");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "unmark") - 1;
        return new UnmarkCommand(index);
    }
}
