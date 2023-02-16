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
     * @param input The user input as a string.
     * @return The corresponding Command object based on the user input.
     * @throws SundayException If the user input is not a valid command.
     */
    public static Command parse(String input) throws SundayException {
        try {
            String command = input.split(" ")[0];
            System.out.println(command.toUpperCase());
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SundayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
