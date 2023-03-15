package jeo.parser;

import jeo.command.Command;
import jeo.command.FindCommand;
import jeo.exception.JeoException;

/**
 * Parses input arguments and creates a FindCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class FindParser implements Parser {

    /**
     * Parses the given input arguments and returns a FindCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return FindCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a keyword.", "find");
        }
        String key = splitInput[1];
        return new FindCommand(key);
    }
}
