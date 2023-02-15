package membot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a helper class that validates and cleanses inputs by users.
 */
public class InputValidator {
    private static final String[] RESERVED = new String[]{"/by", "/to", "/from"};
    private static final int MIN_INPUT_LENGTH = 2;
    private static final String DELIMITER_SPACE = " ";

    /**
     * Validates if commands in the form of <code>[command] [input]</code> is valid.
     *
     * @param input The command to be validated.
     * @param isMulti True if the input contains multiple words delimited by `space` characters.
     * @param isInt True if the input is an integer.
     * @return True if command is valid, else false.
     */
    public static boolean isSingleInputValid(String input, boolean isMulti, boolean isInt) {
        String[] split = input.split(DELIMITER_SPACE);

        if (split.length == 1) {
            return false;
        }

        if (!isMulti && split.length != MIN_INPUT_LENGTH) {
            return false;
        }

        if (!isInt) {
            return true;
        }

        try {
            Integer.parseInt(split[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Cleanses and normalises a <code>todo</code> command.
     *
     * @param input The <code>todo</code> command.
     * @return The normalised command in a form of a <code>String</code> array.
     * @throws InvalidCommandException If the command parsed is invalid.
     */
    public static String[] normaliseTodoInput(String input) throws InvalidCommandException {
        String[] split = input.split(DELIMITER_SPACE);
        List<String> reservedList = Arrays.asList(RESERVED);
        for (String s : split) {
            if (reservedList.contains(s)) {
                throw new InvalidCommandException("There should not be any reserved keywords");
            }
        }

        String[] res = new String[2];
        try {
            res[0] = input.substring(0, 4);
            res[1] = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Unable to normalise input as a Todo input");
        }

        return res;
    }

    /**
     * Cleanses and normalises a <code>deadline</code> command.
     *
     * @param input The <code>deadline</code> command.
     * @return The normalised command in a form of a <code>String</code> array.
     * @throws InvalidCommandException If the command parsed is invalid.
     * @throws NoDeadlineFoundException If no deadline can be found in the command.
     */
    public static String[] normaliseDeadlineInput(String input) throws
            InvalidCommandException, NoDeadlineFoundException {
        String[] split = input.split(DELIMITER_SPACE);
        if (split.length < 4) {
            throw new InvalidCommandException("Invalid Syntax - \"deadline [title] /by [deadline]\" "
                    + "(e.g. \"deadline physics project /by tomorrow 3pm\"");
        }
        int totalKeywords = 0;
        List<String> reservedList = Arrays.asList(RESERVED);
        ArrayList<String> keywords = new ArrayList<>();
        for (String s : split) {
            if (reservedList.contains(s)) {
                totalKeywords++;
                keywords.add(s);
            }
        }

        if (totalKeywords == 0) {
            throw new InvalidCommandException("Missing /by keyword in input!");
        } else if (totalKeywords > 1) {
            throw new InvalidCommandException("Too many keywords in input!");
        } else if (!keywords.get(0).equals("/by")) {
            throw new InvalidCommandException("Missing /by keyword in input!");
        }

        if (split[1].equals("/by")) {
            throw new InvalidCommandException("Empty title");
        }

        String[] res = new String[3];
        try {
            res[0] = input.substring(0, 8);
            res[2] = extractDeadline(input);
            res[1] = input.substring(9, input.indexOf("/by ") - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Unable to normalise input as a Deadline input");
        }

        return res;
    }

    /**
     * Cleanses and normalises an <code>event</code> command.
     *
     * @param input The <code>event</code> command.
     * @return The normalised command in a form of a <code>String</code> array.
     * @throws InvalidCommandException If the command parsed is invalid.
     * @throws NoStartDateTimeFoundException If no start dateTime can be found in the command.
     * @throws NoEndDateTimeFoundException If no end dateTime can be found in the command.
     */
    public static String[] normaliseEventInput(String input) throws InvalidCommandException,
            NoStartDateTimeFoundException, NoEndDateTimeFoundException {
        String[] split = input.split(DELIMITER_SPACE);
        if (split.length < 6) {
            throw new InvalidCommandException("Invalid syntax");
        }
        int totalKeywords = 0;
        List<String> reservedList = Arrays.asList(RESERVED);
        ArrayList<String> keywords = new ArrayList<>();
        String firstKeyword = "";
        for (String s : split) {
            if (reservedList.contains(s)) {
                totalKeywords++;
                keywords.add(s);

                if (firstKeyword.isEmpty()) {
                    firstKeyword = s;
                }
            }
        }

        if (totalKeywords != 2 || !(keywords.contains("/from") && keywords.contains("/to"))) {
            throw new InvalidCommandException("There should only have /from and /to keyword");
        }

        if (split[1].equals("/from") || split[1].equals("/to")) {
            throw new InvalidCommandException("Empty title");
        }

        String[] res = new String[4];
        try {
            res[0] = input.substring(0, 5);
            res[1] = input.substring(6, input.indexOf(firstKeyword + " ") - 1);
            res[2] = extractStartDateTime(input);
            res[3] = extractEndDateTime(input);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Unable to normalise input as a Event input");
        }

        return res;
    }

    private static String extractDeadline(String input) throws NoDeadlineFoundException {
        int deadlineStartIndex = input.indexOf("/by ");

        if (deadlineStartIndex == -1) {
            throw new NoDeadlineFoundException("Unable to find \"/by\" subcommand");
        }

        return input.substring(deadlineStartIndex + 4);
    }

    private static String extractStartDateTime(String input) throws NoStartDateTimeFoundException {
        int dateTimeStartIndex = input.indexOf("/from ");
        if (dateTimeStartIndex == -1) {
            throw new NoStartDateTimeFoundException("Unable to find \"/from\" subcommand");
        }

        String substring = input.substring(dateTimeStartIndex);
        if (substring.split(DELIMITER_SPACE)[1].startsWith("/") || substring.split(DELIMITER_SPACE)[1].isEmpty()) {
            throw new NoStartDateTimeFoundException("Unable to find start dateTime");
        }

        int dateTimeEndIndex = substring.indexOf(" /");
        if (dateTimeEndIndex == -1) {
            return input.substring(dateTimeStartIndex + 6);
        } else {
            return input.substring(dateTimeStartIndex + 6, dateTimeEndIndex + dateTimeStartIndex);
        }
    }

    private static String extractEndDateTime(String input) throws NoEndDateTimeFoundException {
        int dateTimeStartIndex = input.indexOf("/to ");
        if (dateTimeStartIndex == -1) {
            throw new NoEndDateTimeFoundException("Unable to find \"/to\" subcommand");
        }

        String substring = input.substring(dateTimeStartIndex);
        if (substring.split(DELIMITER_SPACE)[1].startsWith("/") || substring.split(DELIMITER_SPACE)[1].isEmpty()) {
            throw new NoEndDateTimeFoundException("Unable to find end dateTime");
        }

        int dateTimeEndIndex = substring.indexOf(" /");
        if (dateTimeEndIndex == -1) {
            return input.substring(dateTimeStartIndex + 4);
        } else {
            return input.substring(dateTimeStartIndex + 4, dateTimeEndIndex + dateTimeStartIndex);
        }
    }
}
