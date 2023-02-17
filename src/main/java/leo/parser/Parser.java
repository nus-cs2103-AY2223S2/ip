package leo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Encapsulates the parsing functions of user input.
 */
public class Parser {
    private Scanner io;

    /**
     * Creates a new Parser object that reads user input.
     */
    public Parser() {
        io = new Scanner(System.in);
    }

/**
     * Parses the user input into a String array.
     * @return String array of user input.
     */
    public String[] parseRequest() {
        String request = io.nextLine();
        assert request != null : "Request should not be null";
        return request.split(" ", 2);
    }

    /**
     * Parses the user input into a String array.
     * @param request
     * @return String array of user input.
     */
    public String[] parseRequest(String request) {
        return request.split(" ", 2);
    }


    /**
     * Parses the task ID from the user input.
     * @param taskID
     * @return
     */
    public static int getTaskID(String taskID) {
        return Integer.parseInt(taskID) - 1;
    }

    /**
     * Closes the Scanner object.
     */
    public void close() {
        io.close();
    }

    /**
     * Parses the date and time from the user input.
     * @param dateTimeString
     * @return
     */
    public static LocalDateTime stringToDate(String dateTimeString) {
        LocalDateTime date = LocalDateTime.parse(dateTimeString.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return date;
    }
}