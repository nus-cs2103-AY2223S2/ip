package cluck.parser;

import cluck.commands.Command;
/**
 * Parser class takes user input and parses it into commands
 * that can be executed by Cluck in other packages.
 */
public class Parser {
    private static final String MAKE_DEADLINE = "deadline";
    private static final String MAKE_TODO = "todo";
    private static final String MAKE_EVENT = "event";
    private static final String DUE_DATE_FLAG = "/by ";
    private static final String EVENT_START_FLAG = "/from ";
    private static final String EVENT_END_FLAG = "/to ";

    private static final String SAVE_DIR_STRING = "SavedData";
    private static final String SAVE_FILE_STRING = "CluckSave.txt";

    /**
     * @param strNum String of interest.
     * @return returns true if strNum is a number in string format, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Takes the string input of the user and converts it into an executable command.
     *
     * @return sublcass of Command
     */
    public static Command commandFactory() {

    }
}
