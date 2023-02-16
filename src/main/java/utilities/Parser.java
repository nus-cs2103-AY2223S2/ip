package utilities;

import command.Command;
import exceptions.SundayException;

/**
 * The Parser class is used to parse the user input and convert it into a {@link Command} object.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand A string array that contains the user input.
     * @return The corresponding Command object based on the user input.
     * @throws SundayException If the user input is not a valid command.
     */
    public static Command parse(String[] fullCommand) throws SundayException {
        try {
            return Command.valueOf(fullCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SundayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
