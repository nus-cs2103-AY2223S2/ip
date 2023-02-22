package wessy.components;

import java.util.stream.IntStream;

import wessy.CmdType;
import wessy.exceptions.MissingSpacingException;
import wessy.exceptions.TimeSpecifierException;
import wessy.exceptions.UnspecifiedTimeException;

import wessy.exceptions.numofinput.MissingInputException;
import wessy.exceptions.numofinput.TooManyInputException;

import wessy.exceptions.integer.NotAnIntegerException;

/**
 * UserInputChecker is a utility class that checks for all the formatting issues
 * that could be possibly found in user input (except for the date time related
 * one which will be checked by the Parser class).
 */
public class UserInputChecker {
    private static final int START = 0;
    private static final char SPACE = ' ';
    private static final String SPACE_AS_STR = Character.toString(SPACE);
    private static final char DATE_SEPARATOR = '-';
    private static final String BY_SPECIFIER = "/by";
    private static final String FROM_SPECIFIER = "/from";
    private static final String TO_SPECIFIER = "/to";

    /**
     * Throws MissingSpaceException when there is a missing space after the
     * first word, which is usually the command word, in the user input line.
     * This method is only used for the "mark", "unmark", "delete", "todo",
     * "deadline" and "event" commands.
     *
     * @param userInput The line of user input to be checked.
     * @param cmd The specified command. Only accepts CmdType.TODO,
     *            CmdType.DEADLINE, CmdType.EVENT, CmdType.MARK, CmdType.UNMARK,
     *            and CmdType.DELETE.
     * @throws MissingSpacingException If the space is missing after the command word.
     */
    public static void checkSpacingAftCmd(String userInput, CmdType cmd) throws MissingSpacingException {
        if (isCmdThatNeedsInput(cmd)) {
            assert cmd == CmdType.TODO || cmd == CmdType.DEADLINE || cmd == CmdType.EVENT ||
                    cmd == CmdType.MARK || cmd == CmdType.UNMARK || cmd == CmdType.DELETE;
                    
            boolean noSpacing = userInput.equals(cmd.toString()) || userInput.charAt(cmd.getStrLength()) != SPACE;
            if (noSpacing) {
                throw new MissingSpacingException(cmd.toString());
            }
        }
    }

    /**
     * Throws MissingInputException if the commands that require some inputs
     * (namely "todo", "deadline", "event", "mark", "unmark" and "delete") have
     * missing input.
     *
     * @param userInput The line of user input to be checked.
     * @param cmd The specified command. Only accepts CmdType.TODO,
     *            CmdType.DEADLINE, CmdType.EVENT, CmdType.MARK, CmdType.UNMARK,
     *            and CmdType.DELETE.
     * @throws MissingInputException If the input after the command word is missing.
     */
    public static void checkMissingInput(String userInput, CmdType cmd) throws MissingInputException {
        String remainingStr = userInput.substring(cmd.getStrLength());
        if (isCmdThatNeedsInput(cmd) && isAllSpaces(remainingStr)) {
        
            assert cmd == CmdType.TODO || cmd == CmdType.DEADLINE || cmd == CmdType.EVENT ||
                    cmd == CmdType.MARK || cmd == CmdType.UNMARK || cmd == CmdType.DELETE;
            throw new MissingInputException(cmd.toString());
        }
    }

    /**
     * Throws TimeSpecifierException if the substring " /by" is missing for
     * deadline command, or if the substring " /from" or " /to" is missing for event command.
     *
     * @param userInput The line of user input to be checked.
     * @param cmd The specified command. Only accepts CmdType.DEADLINE and
     *            CmdType.EVENT.
     * @throws TimeSpecifierException If the required time specifier keyword is missing.
     */
    public static void checkMissingKeyword(String userInput, CmdType cmd) throws TimeSpecifierException {
        String timeSpecifier = null;

        if (cmd == CmdType.DEADLINE && !userInput.contains(SPACE_AS_STR + BY_SPECIFIER)) {
            timeSpecifier = BY_SPECIFIER;
        } else if (cmd == CmdType.EVENT) {
            if (!userInput.contains(SPACE_AS_STR + FROM_SPECIFIER)) {
                timeSpecifier = FROM_SPECIFIER;
            } else if (!userInput.contains(SPACE_AS_STR + TO_SPECIFIER)) {
                timeSpecifier = TO_SPECIFIER;
            }
        }

        if (timeSpecifier != null) {
            throw new TimeSpecifierException(timeSpecifier);
        }
    }

    /**
     * Throws UnspecifiedTimeException if the "/by" time is not specified for
     * the deadline command.
     *
     * @param userInput The line of user input to be checked.
     * @throws UnspecifiedTimeException if the "/by" time input is missing for
     * the deadline command.
     */
    public static void checkDeadlineMissingInput(String userInput) throws UnspecifiedTimeException {
        int pos = userInput.indexOf(BY_SPECIFIER);
        UnspecifiedTimeException ex = null;

        if (isAllSpaces(userInput.substring(CmdType.DEADLINE.getStrLength(), pos))) {
            ex = new UnspecifiedTimeException(BY_SPECIFIER, true);
        } else if (isAllSpaces(userInput.substring(pos + BY_SPECIFIER.length()))) {
            ex = new UnspecifiedTimeException(BY_SPECIFIER, false);
        }

        if (ex != null) {
            throw ex;
        }
    }

    /**
     * Throws UnspecifiedTimeException if the "/from" or "/to" time is not
     * specified for the event command.
     *
     * @param userInput The line of user input to be checked.
     * @throws UnspecifiedTimeException if any of the time input is missing for
     * the event command.
     */
    public static void checkEventMissingInput(String userInput) throws UnspecifiedTimeException {
        int fPos = userInput.indexOf(FROM_SPECIFIER);
        int tPos = userInput.indexOf(TO_SPECIFIER);

        UnspecifiedTimeException ex = null;
        if (isAllSpaces(userInput.substring(CmdType.EVENT.getStrLength(), fPos))) {
            ex = new UnspecifiedTimeException(FROM_SPECIFIER, true);
        } else if (isAllSpaces(userInput.substring(fPos + FROM_SPECIFIER.length(), tPos))) {
            ex = new UnspecifiedTimeException(FROM_SPECIFIER, false);
        } else if (isAllSpaces(userInput.substring(tPos + TO_SPECIFIER.length()))) {
            ex = new UnspecifiedTimeException(TO_SPECIFIER, false);
        }

        if (ex != null) {
            throw ex;
        }
    }

    /**
     * Throws NotAnIntegerException when there are character in the String that
     * is not a digit nor a space (" ").
     *
     * @param userInput The line of user input to be checked.
     * @param cmd The specified command. Only accepts CmdType.MARK,
     *            CmdType.UNMARK and CmdType.DELETE.
     * @throws NotAnIntegerException If any invalid character is found in the String.
     */
    public static void checkNotNumerical(String userInput, CmdType cmd) throws NotAnIntegerException {
        String str = userInput.substring(cmd.getStrLength());
        
        boolean isNumerical = str.chars().allMatch(UserInputChecker::isValidChar);
        if (!isNumerical) {
            throw new NotAnIntegerException();
        }
    }

    private static boolean isValidChar(int target) {
        int numOfDigits = 10;
        int[] extras = new int[]{(int) ' ', (int) '-'};
        
        return IntStream.concat(IntStream.range(START, numOfDigits).map(i -> i + '0'), IntStream.of(extras))
                .anyMatch(c -> target == c);
    }

    /**
     * Throws TooManyInputException when there are too many inputs after the
     * first word (the command word). This method does so by checking is there
     * any spacing in the rest of the user input line. Remember that the "mark",
     * "unmark" and "delete" commands only accept one input.
     *
     * @param userInput The line of user input to be checked.
     * @param cmd The specified command. Only accepts CmdType.MARK,
     *            CmdType.UNMARK and CmdType.DELETE.
     * @throws TooManyInputException If there are too many inputs after the
     * command word.
     */
    public static void checkTooManyInputs(String userInput, CmdType cmd) throws TooManyInputException {
        String str = Parser.removeSpacePadding(userInput.substring(cmd.getStrLength()));
        
        if (str.chars().anyMatch(UserInputChecker::isSpace)) {
            throw new TooManyInputException(cmd.toString());
        }
    }

    /**
     * A helper function to check does str only consist of space (" ").
     *
     * @param str A String to be checked.
     * @return A boolean value indicating does str only consist of space (" ").
     */
    private static boolean isAllSpaces(String str) {
        return str.chars().allMatch(UserInputChecker::isSpace);
    }

    private static boolean isSpace(int c) {
        return c == (int) ' ';
    }

    /**
     * A helper function to check is cmd CmdType.TODO, CmdType.DEADLINE,
     * CmdType.EVENT, CmdType.MARK, CmdType.UNMARK or CmdType.DELETE.
     *
     * @param cmd The specified command.
     * @return A boolean value indicating is the command todo, deadline, event,
     * mark, unmark or delete.
     */
    private static boolean isCmdThatNeedsInput(CmdType cmd) {
        return (cmd == CmdType.TODO || cmd == CmdType.DEADLINE || cmd == CmdType.EVENT || cmd == CmdType.MARK
                || cmd ==  CmdType.UNMARK || cmd == CmdType.DELETE);
    }
}
