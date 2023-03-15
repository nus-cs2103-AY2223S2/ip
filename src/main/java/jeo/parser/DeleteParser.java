package jeo.parser;

import jeo.command.Command;
import jeo.command.DeleteCommand;
import jeo.exception.JeoException;


/**
 * Parses input arguments and creates a DeleteCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class DeleteParser implements Parser {

    /**
     * Parses the given input arguments and returns a DeleteCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return DeleteCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        if (splitInput.length == 1) {
            throw new JeoException("Please enter a task number.", "delete");
        }
        int index = ParserUtil.parseIndex(splitInput[1], "delete") - 1;
        return new DeleteCommand(index);
    }
}
