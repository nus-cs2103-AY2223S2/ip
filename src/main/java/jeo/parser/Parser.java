package jeo.parser;

import jeo.command.Command;
import jeo.exception.JeoException;

/**
 * Represents a Parser that is able to parse user input into a command.
 * @author Goh Jun How
 * @version 0.3
 */
public interface Parser {
    /**
     * Parses user input into a command and returns it.
     * @param splitInput user input split by white spaces
     * @return The command
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    Command parse(String[] splitInput) throws JeoException;
}
