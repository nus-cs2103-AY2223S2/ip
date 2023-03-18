package jeo.parser;

import jeo.command.Command;
import jeo.command.DueCommand;
import jeo.exception.JeoException;

/**
 * Parses input arguments and creates a DueCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class DueParser implements Parser {

    /**
     * Parses the given input arguments and returns a DueCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return DueCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a date.", "due");
        }
        String by = splitInput[1];
        return new DueCommand(by);
    }
}
