package wessy;

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
        if (isCmdThatNeedsInput(cmd) && (userInput.equals(cmd.toString())
                || userInput.charAt(cmd.getStrLength()) != ' ')) {
            throw new MissingSpacingException(cmd.toString());
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
        if (isCmdThatNeedsInput(cmd) && isAllSpaces(userInput.substring(cmd.getStrLength()))) {
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
    public static void checkMissingKeyword(String userInput, CmdType cmd) throws
            TimeSpecifierException {
        TimeSpecifierException ex = null;
        if (cmd == CmdType.DEADLINE && !userInput.contains(" /by")) {
            ex = new TimeSpecifierException("/by");
        } else if (cmd == CmdType.EVENT) {
            if (!userInput.contains(" /from")) {
                ex = new TimeSpecifierException("/from");
            } else if (!userInput.contains(" /to")) {
                ex = new TimeSpecifierException("/to");
            }
        }

        if (ex != null) {
            throw ex;
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
        String keyword = "/by";
        int pos = userInput.indexOf(keyword);

        UnspecifiedTimeException ex = null;
        if (isAllSpaces(userInput.substring("deadline".length(), pos))) {
            ex = new UnspecifiedTimeException(keyword, true);
        } else if (isAllSpaces(userInput.substring(pos + keyword.length()))) {
            ex = new UnspecifiedTimeException(keyword, false);
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
        String fKeyword = "/from";
        String tKeyword = "/to";
        int fPos = userInput.indexOf(fKeyword);
        int tPos = userInput.indexOf(tKeyword);

        UnspecifiedTimeException ex = null;
        if (isAllSpaces(userInput.substring("event".length(), fPos))) {
            ex = new UnspecifiedTimeException(fKeyword, true);
        } else if (isAllSpaces(userInput.substring(fPos + fKeyword.length(), tPos))) {
            ex = new UnspecifiedTimeException(fKeyword, false);
        } else if (isAllSpaces(userInput.substring(tPos + tKeyword.length()))) {
            ex = new UnspecifiedTimeException(tKeyword, false);
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
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7'
                    && c != '8' && c != '9' && c != ' ' && c != '-') {
                throw new NotAnIntegerException();
            }
        }
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
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == ' ') {
                throw new TooManyInputException(cmd.toString());
            }
        }
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

    /**
     * A helper function to check does str only consist of space (" ").
     *
     * @param str A String to be checked.
     * @return A boolean value indicating does str only consist of space (" ").
     */
    private static boolean isAllSpaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
