package cbot.io;

import java.util.ArrayList;
import java.util.stream.Collectors;

import cbot.command.BadInputException;
import cbot.command.PoorInputException;

/**
 * Manages printing to and receiving from the user, mainly via the Command Line Interface.
 */
public class Talker {

    //CHECKSTYLE.OFF: SingleSpaceSeparator

    // Frequently Used Strings
    private static final String BLANK     = "";
    private static final String INDENT    = BLANK + " ~ ";
    private static final String WARNING   = BLANK + "!! ";
    private static final String ERROR     = WARNING + "<Error> ";

    //CHECKSTYLE.ONL SingleSpaceSeparator

    /**
     * Returns Cbot's friendly greeting :D
     *
     * @return Cbot's greeting.
     */
    public static String sayHi() {
        String cbotLogo = "  _____  _            _\n"
                + " /  ___]| |   ^-^   _| |_\n"
                + "|  / :D | |___  ___[_ + _]\n"
                + "|  \\___ | / . \\/ . \\ | |\n"
                + " \\_____]|_,_*_/\\_*_/ |_/\n\n";

        return cbotLogo + "How can I help you today?";
    }

    /**
     * Returns Cbot's closing words.
     *
     * @return Cbot's farewell.
     */
    public static String sayBye() {
        return INDENT + "See you again!";
    }

    /**
     * Returns a soft warning of the given exception.
     *
     * @param e The input exception.
     * @return A soft exception warning.
     * @see PoorInputException
     */
    public static String warn(Exception e) {
        return WARNING + e.getMessage();
    }

    /**
     * Returns a warning of the given exception.
     *
     * @param e The input exception.
     * @return A hard exception warning.
     * @see BadInputException
     */
    public static String warnBad(Exception e) {
        return ERROR + e.getMessage();
    }

    /**
     * Returns a suggestion for formatting datetime. This is usually raised
     * when the user provides an unrecognized datetime format.
     *
     * @return The datetime formatting suggestion.
     */
    public static String warnTime() {
        return WARNING + "Sorry, I don't know how to interpret that datetime."
                + "Try something in the form:\n"
                + "yyyy-MM-dd HH:mm";
    }

    /**
     * Returns the given String with the standard indentation.
     *
     * @param str The String to print.
     * @return The indented input.
     */
    public static String say(String str) {
        return INDENT + str;
    }

    /**
     * Returns a String of a list of strings, such that each is indented properly.
     *
     * @param arr The list of strings to print.
     * @return An indented String list.
     */
    public static String printMany(ArrayList<String> arr) {
        return arr.stream()
                .map(s -> BLANK + s)
                .collect(Collectors.joining("\n"));
    }
}
