package wessy;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import wessy.exceptions.integer.NotPositiveIntegerException;

/**
 * Parser is a utility class that mainly processes the user input when "Wessy"
 * first receives it. While it checks for some formats of the String, it
 * delegates checking of the other formatting issues to UserInputChecker class.
 */
public class Parser {
    /**
     * Converts the first word in the line of user input, to the corresponding
     * CmdType, if it is a valid command type.
     *
     * @param userInput The line which the user inputs.
     * @return The corresponding CmdType, if the first word is a valid command.
     */
    static CmdType getCmd(String userInput) {
        int idx = userInput.indexOf(' ');
        if (idx == -1) {
            idx = userInput.length();
        }
        return CmdType.getCmdType(userInput.substring(0, idx));
    }

    /**
     * Parses the line of user input and chunks it into the different components
     * required to initialise a ToDo, Deadline or Event object. The components
     * are possibly the task description, and the timings specified for the
     * deadline task or the event.
     *
     * @param userInput The line which the user inputs.
     * @param cmd The command specified, in the form of CmdType. It only accepts
     *           CmdType.TODO, CmdType.DEADLINE and CmdType.EVENT.
     * @return An array of Strings consisting of the components required to
     * initialise a task.
     */
    static String[] getTaskComponents(String userInput, CmdType cmd) {
        String byStr = "/by";
        String fromStr = "/from";
        String toStr = "/to";
        int firstIdx;
        int secondIdx;
        String description = userInput.substring(cmd.getStrLength() + 1);

        switch (cmd) {
        case TODO:
            return new String[]{description};
        case DEADLINE:
            firstIdx = description.indexOf(byStr);
            return new String[]{removeSpacePadding(description.substring(0, firstIdx)),
                    removeSpacePadding(description.substring(firstIdx + byStr.length()))};
        case EVENT:
            firstIdx = description.indexOf(fromStr);
            secondIdx = description.indexOf(toStr);
            return new String[]{removeSpacePadding(description.substring(0, firstIdx)),
                    removeSpacePadding(description.substring(firstIdx + fromStr.length(), secondIdx)),
                    removeSpacePadding(description.substring(secondIdx + toStr.length()))};
        }

        return new String[0];
    }

    /**
     * Parses the input str and converts it into a LocalDateTime object.
     *
     * @param str The specified date and time as a String.
     * @return A LocalDateTime object that is represented by str, in its String
     * form.
     * @throws DateTimeParseException If the format of str is wrong and thus
     * cannot be parsed into a LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String str) throws
            DateTimeParseException {
        str = removeSpacePadding(str);

        if (count(str, ':') == 2) {
            return LocalDateTime.parse(str);
        }
        int n = str.length();
        if (n <= 10) {
            return LocalDateTime.parse(standardiseDateFormat(str) + "T12:34:56");
        }

        int idx = 10;
        if (str.charAt(9) == ' ') {
            idx = 9;
        } else if (str.charAt(8) == ' ') {
            idx = 8;
        }
        if (str.charAt(idx + 3) == ':') {
            return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                    + str.substring(idx + 1) + ":00");
        }
        if (str.charAt(idx + 3) == '.') {
            return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                    + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 4) + ":00");
        }

        return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 3) + ":00");
    }

    /**
     * Counts the number of occurrence "target" appears in str.
     *
     * @param str The String we scan through while counting the number of
     *            occurrence.
     * @param target The character we look out for when scanning through str.
     * @return The number of occurrence "target" appears in str.
     */
    static int count(String str, char target) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                num++;
            }
        }
        return num;
    }

    /**
     * Standardises format of date and time in str, by say making sure that the
     * separator in the date is "-" instead of "/", making sure the date has 8
     * digits. This is to prepare for the execution of parseDateTime(...).
     *
     * @param str The specified date and time in String form.
     * @return A standardised String format of the specified date and time.
     * @throws DateTimeParseException If str does not represent any date and time.
     */
    private static String standardiseDateFormat(String str) throws DateTimeParseException {
        try {
            String[] components = str.split("-", 3);

            if (str.contains("/")) {
                components = str.split("/", 3);
            }

            for (int i = 0; i < components.length; i++) {
                if (components[i].length() == 1) {
                    components[i] = "0" + components[i];
                }
            }

            if (components[0].length() == 4) {
                return components[0] + "-" + components[1] + "-" + components[2];
            }
            return components[2] + "-" + components[1] + "-" + components[0];

        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DateTimeParseException("", str, 0);
        }
    }

    /**
     * Removes the command word from the user input and then the space paddings
     * on the 2 sides. Afterwards, converts the processed String into a integer
     * using Integer.parseInt(...).
     *
     * @param userInput The String to be parsed to an integer.
     * @param cmd The specified command.
     * @return The integer to which the String is converted.
     * @throws NotPositiveIntegerException If the output integer is not positive.
     */
    public static int parseInt(String userInput, CmdType cmd) throws NotPositiveIntegerException {
        int num = Integer.parseInt(removeSpacePadding(userInput.substring(cmd.getStrLength())));
            if (num <= 0) {
                throw new NotPositiveIntegerException();
            }
        return num;
    }

    /**
     * A helper function that removes the space paddings on the two ends of str.
     *
     * @param str The String to be processed.
     * @return The shorter String after removing the space paddings on the two ends.
     */
    public static String removeSpacePadding(String str) {
        int start = 0;
        while (str.charAt(start) == ' ') {
            start++;
        }

        int end = str.length() - 1;
        while (str.charAt(end) == ' ') {
            end--;
        }

        return str.substring(start, end + 1);
    }
}
