package duke.command;

import java.util.ArrayList;
import java.util.Collections;

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
    public abstract String execute() throws DukeException;

    /**
     * Check the command line for the correct taskName syntax
     * throws exceptions for missing name or wrong keyword
     */
    public static String getTaskName(String type, String input) throws DukeException {
        String endWord = type.equals("deadline") ? "/by" : "/from";

        int startIndex = 0;
        int endIndex = input.indexOf(endWord);
        System.out.println(endIndex);
        if (endIndex == startIndex || input.trim().isEmpty()) {
            throw new DukeException("OOPS!!! Missing Task Name.");
        }

        if (endIndex == -1) {
            throw new DukeException("OOPS!!! Missing" + endWord + " keyword.");
        }

        return input.substring(startIndex, endIndex);

    }

    /*
     * getStartDate checks the command line for the correct startDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getStartDate(String input) throws DukeException {
        String startWord = "/from";
        String endWord = " /to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;
        int endIndex = input.indexOf(endWord);
        if (startIndex > input.length()) {
            throw new DukeException("OOPS!!! Missing Start Date.");
        }
        if (endIndex == -1) {
            throw new DukeException("OOPS!!! Missing" + endWord + " keyword.");
        }
        if (endIndex < startIndex) {
            throw new DukeException("OOPS!!! Missing Start Date.");
        }
        return input.substring(startIndex, endIndex);
    }

    /*
     * getEndDate checks the command line for the correct endDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getEndDate(String type, String input) throws DukeException {
        String keyword = type.equals("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(keyword) + keyword.length() + 1;
        if (startIndex > input.length()) {
            throw new DukeException("OOPS!!! Missing End Date.");
        }
        return input.substring(startIndex);
    }

    public static ArrayList<String> getKeyword(String... inputs) {

        ArrayList<String> keywords = new ArrayList<>();

        Collections.addAll(keywords, inputs);

        System.out.println(keywords.size());

        return keywords;


    }
}
