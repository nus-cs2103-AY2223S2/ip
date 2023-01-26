package cbot.io;

import cbot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages printing to and receiving from the user, mainly via the Command Line Interface.
 */
public class UI {
    private final Scanner sc;
    
    // Frequently Used Strings
    private static final String BLANK     = "        ";
    private static final String INDENT    = "      ~ ";
    private static final String WARNING   = "     !! ";
    private static final String ERROR     = WARNING + "<Error> ";
    private static final String PROMPT    = "\n v v\n";
    private static final String STALL     = "\n   o\n   o\n   o\n\n";

    /**
     * Constructs a new instance to manage the CLI.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prompts the user to input text and receives it.
     *
     * @return The user input.
     * @throws BadInputException If the input includes the separator string used by the save file.
     * @see cbot.task.Task#makeFileFriendly()
     */
    public String askUser() throws BadInputException {
        System.out.println(PROMPT);
        String userInput = this.sc.nextLine();
        System.out.println();
        
        if (userInput.contains(Task.SEP)) {
            throw new BadInputException("Please avoid using: \"" + Task.SEP + "\"");
        }
        
        return userInput;
    }

    /**
     * Prints that a new save file has been created.
     *
     * @param path Directory location (relative) of the save file.
     */
    public static void sayNewFile(String path) {
        System.out.println(WARNING + "No save file found. Making a new save at " + path);
    }

    /**
     * Prints Cbot's friendly greeting :D
     */
    public static void sayHi() {
        String dukeLogo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        
        String cbotLogo = "  _____  _            _\n"
                + " /  ___]| |   ^-^   _| |_\n"
                + "|  / :D | |___  ___[_ + _]\n"
                + "|  \\___ | / . \\/ . \\ | |\n"
                + " \\_____]|_,_*_/\\_*_/ |_/\n";
        
        System.out.println(STALL + INDENT + "Hey! D:< I'm not\n" + dukeLogo);
        System.out.println(INDENT + "I'm\n" + cbotLogo);
        System.out.println(STALL + INDENT
                + "How can I help you today?");
    }

    /**
     * Prints Cbot's closing words.
     */
    public static void sayBye() {
        System.out.println(STALL + INDENT + "See you again!");
    }

    /**
     * Prints the given exception as a soft warning.
     *
     * @param e The input exception.
     * @see PoorInputException
     */
    public static void warn(Exception e) {
        System.out.println(WARNING + e.getMessage());
    }

    /**
     * Prints the given exception as a warning.
     *
     * @param e The input exception.
     * @see BadInputException
     */
    public static void warnBad(Exception e) {
        System.out.println(ERROR + e.getMessage());
    }

    /**
     * Prints a suggestion for formatting datetime. This is usually raised
     * when the user provides an unrecognized datetime format.
     */
    public static void warnTime() {
        System.out.println(ERROR + "Sorry, I don't know how to interpret that datetime");
        System.out.println(WARNING + "Try something in the form: yyyy-MM-dd HH:mm");
    }

    /**
     * Prints the given String with the standard indentation.
     *
     * @param str The String to print.
     */
    static void say(String str) {
        System.out.println(INDENT + str);
    }

    /**
     * Prints a list of strings, such that each is indented properly.
     *
     * @param arr The list of strings to print.
     */
    static void printMany(ArrayList<String> arr) {
        for (String s : arr) {
            System.out.println(BLANK + s);
        }
    }
}