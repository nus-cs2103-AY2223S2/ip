package wessy;

import wessy.exceptions.MissingSpacingException;
import wessy.exceptions.TimeSpecifierException;
import wessy.exceptions.UnspecifiedTimeException;

import wessy.exceptions.numofinput.MissingInputException;
import wessy.exceptions.numofinput.TooManyInputException;

import wessy.exceptions.integer.NotAnIntegerException;

public class UserInputChecker {
    // Check for "Missing space after command"
    public static void checkSpacingAftCmd(String userInput, CmdType cmd) throws MissingSpacingException {
        if (isCmdThatNeedsInput(cmd) && (userInput.equals(cmd.toString())
                || userInput.charAt(cmd.getStrLength()) != ' ')) {
            throw new MissingSpacingException(cmd.toString());
        }
    }

    // Check simpler missing input case (excluding "/by", "/from", "/to" keywords)
    public static void checkMissingInput(String userInput, CmdType cmd) throws MissingInputException {
        if (isCmdThatNeedsInput(cmd) && isAllSpaces(userInput.substring(cmd.getStrLength()))) {
            throw new MissingInputException(cmd.toString());
        }
    }

    // Check for existence of " /by ", " /from " or " /to "
    public static void checkMissingKeyword(String userInput, CmdType cmd) throws TimeSpecifierException {
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

    // Check deadline's missing input time
    public static void checkDeadlineMissingInput(String userInput, CmdType cmd) throws UnspecifiedTimeException {
        String keyword = "/by";
        UnspecifiedTimeException ex = null;
        int pos = userInput.indexOf(keyword);
        if (isAllSpaces(userInput.substring(cmd.getStrLength(), pos))) {
            ex = new UnspecifiedTimeException(keyword, true);
        } else if (isAllSpaces(userInput.substring(pos + keyword.length()))) {
            ex = new UnspecifiedTimeException(keyword, false);
        }
        if (ex != null) {
            throw ex;
        }
    }

    // Check event's missing input time
    public static void checkEventMissingInput(String userInput, CmdType cmd) throws UnspecifiedTimeException {
        String fKeyword = "/from";
        String tKeyword = "/to";
        int fPos = userInput.indexOf(fKeyword);
        int tPos = userInput.indexOf(tKeyword);

        UnspecifiedTimeException ex = null;
        if (isAllSpaces(userInput.substring(cmd.getStrLength(), fPos))) {
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

    // For "mark", "unmark", "delete", check for non-numerical characters
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

    // For "mark", "unmark", "delete", check for "Too many inputs" error
    public static void checkTooManyInputs(String userInput, CmdType cmd) throws TooManyInputException {
        String str = Parser.removeSpacePadding(userInput.substring(cmd.getStrLength()));
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == ' ') {
                throw new TooManyInputException(cmd.toString());
            }
        }
    }

    // HELPER FUNC
    private static boolean isCmdThatNeedsInput(CmdType cmd) {
        return (cmd == CmdType.TODO || cmd == CmdType.DEADLINE || cmd == CmdType.EVENT || cmd == CmdType.MARK
                || cmd ==  CmdType.UNMARK || cmd == CmdType.DELETE);
    }

    // HELPER FUNC
    private static boolean isAllSpaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
