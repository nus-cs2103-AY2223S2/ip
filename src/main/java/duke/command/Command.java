package duke.command;

import duke.exception.DukeException;

/**
 * Executable commands based on user input
 *
 * @author Guo-KeCheng
 */
public abstract class Command {

    /*
     * Method called to handle the various command types
     * @ throws DukeException
     *
     */
    public abstract boolean execute() throws DukeException;

    /*
     * getTaskName checks the command line for the correct taskName syntax
     * throws exceptions for missing name or wrong keyword
     */
    public static String getTaskName(String type, String command) throws DukeException {
        if (type.equals("todo")) {
            int firstSpace = command.indexOf(" ");
            if (firstSpace == -1) {
                throw new DukeException("☹ OOPS!!! Missing Task Name.");
            } else {
                return command.substring(firstSpace + 1);
            }
        } else {
            String startWord = type.equals("deadline") ? "deadline " : "event ";
            String endWord = type.equals("deadline") ? " /by" : " /from";

            int startIndex = command.indexOf(startWord) + startWord.length();
            int endIndex = command.indexOf(endWord);
            if (startIndex < startWord.length()) {
                throw new DukeException("☹ OOPS!!! Missing Task Name.");
            }
            if (endIndex == -1) {
                throw new DukeException("☹ OOPS!!! Missing" + endWord + " keyword.");
            }
            if (endIndex < startIndex) {
                throw new DukeException("☹ OOPS!!! Missing Task Name.");
            }
            return command.substring(startIndex, endIndex);
        }
    }

    /*
     * getStartDate checks the command line for the correct startDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getStartDate(String command) throws DukeException {
        String startWord = "/from";
        String endWord = " /to";
        int startIndex = command.indexOf(startWord) + startWord.length() + 1;
        int endIndex = command.indexOf(endWord);
        if (startIndex > command.length()) {
            throw new DukeException("☹ OOPS!!! Missing Start Date.");
        }
        if (endIndex == -1) {
            throw new DukeException("☹ OOPS!!! Missing" + endWord + " keyword.");
        }
        if (endIndex < startIndex) {
            throw new DukeException("☹ OOPS!!! Missing Start Date.");
        }
        return command.substring(startIndex, endIndex);
    }

    /*
     * getEndDate checks the command line for the correct endDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getEndDate(String type, String command) throws DukeException {
        String keyword = type.equals("deadline") ? "/by" : "/to";
        int startIndex = command.indexOf(keyword) + keyword.length() + 1;
        if (startIndex > command.length()) {
            throw new DukeException("☹ OOPS!!! Missing End Date.");
        }
        return command.substring(startIndex);
    }

    public static String getKeyword(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 1) {
            throw new DukeException("☹ OOPS!!! Missing Keyword.");
        }
        return command.substring(words[0].length() + 1);
    }
}
