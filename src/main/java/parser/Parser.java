package parser;

import command.Command;
import dukeexception.commandexception.EmptyCommandException;
import dukeexception.commandexception.InputFormatException;

/**
 * Parser for user commands.
 */
public class Parser {
    /**
     * Parses the response given by the user.
     * @param response Response given by the user.
     * @return A Command object best representing the user's input.
     */
    public static Command parse(String response) {
        String strippedResponse = response.strip();
        if (strippedResponse.equals("")) {
            throw new EmptyCommandException();
        }
        String[] commandWordContent = (strippedResponse + " ").split(" ", 2);
        return Command.create(commandWordContent);
    }

    /**
     * Tries to parse a string into an integer and throws an error otherwise.
     * @param rawInt The string to convert into int.
     * @param source Where this method is called, in order to throw a specified exception.
     * @return The int if it can be converted.
     */
    public static int parseInt(String rawInt, String source) {
        try {
            return Integer.parseInt(rawInt.strip());
        } catch (NumberFormatException ex) {
            throw new InputFormatException(source, "Haiya this not number. FAILURE.", null);
        }
    }

    /**
     * Handles a missing field, if any.
     * @param content The content to be parsed.
     * @param delimiter The delimiter to split the string.
     * @param fieldName The field name associated with this operation.
     * @param source Where this method is called, for throwing more specific exceptions.
     * @return The splitted content, if applicable.
     */
    public static String[] handleMissingField(String content, String delimiter, String fieldName, String source) {
        String[] splitted = content.split(delimiter, 2);
        if (splitted.length <= 1) {
            throw new InputFormatException(source,
                    String.format("where your %s? FAILURE.", fieldName), null);
        }
        return splitted;
    }

    /**
     * Handles fields that are there, but are empty.
     * @param field The field to be checked.
     * @param fieldName The name of the first being checked.
     * @param source Where this method is called, for the purposes of exception handling.
     */
    public static void handleEmptyField(String field, String fieldName, String source) {
        if (field.equals("")) {
            throw new InputFormatException(source,
                    String.format("Haiya %s empty. FAILURE.", fieldName), null);
        }
    }
}
