package Parser;

import Command.Command;
import DukeException.CommandException.EmptyCommandException;
import DukeException.CommandException.InputFormatException;

public class Parser {
    public static Command parse(String response) {
        /**
         * @param response the string response given by the user.
         */
        String strippedResponse = response.strip();
        if (strippedResponse.equals("")) {
            throw new EmptyCommandException();
        }
        String[] commandWordContent = (strippedResponse + " ").split(" ", 2);
        return Command.create(commandWordContent);
    }

    public static int parseInt(String s, String source) {
        try {
            return Integer.parseInt(s.strip())-1;
        } catch (NumberFormatException ex) {
            throw new InputFormatException(source, "Haiya this not number. FAILURE.", null);
        }
    }

    public static String[] handleMissingField(String content, String delimiter, String fieldName, String source) {
        /**
         * Handles completely missing fields.
         */
        String[] splitted = content.split(delimiter, 2);
        if (splitted.length <= 1) {
            throw new InputFormatException(source,
                    String.format("Haiya where your %s? FAILURE.", fieldName), null);
        }
        return splitted;
    }

    public static void handleEmptyField(String field, String fieldName, String source) {
        /**
         * The slashes are there but the field is empty.
         */
        if (field.equals("")) {
            throw new InputFormatException(source,
                    String.format("Haiya %s empty. FAILURE.", fieldName), null);
        }
    }
}
