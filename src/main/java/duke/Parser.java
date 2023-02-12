package duke;

import java.util.Arrays;

/**
 * Parser is a class that deals with making sense of the user command, this includes
 * getting the description of a certain task, getting the index from the mark, unmark and delete
 * commands, getting the deadline of a Deadline object and getting the start DateTime 
 * and end DateTime of an Event Object
 */
public class Parser {

    /**
     * Returns an index from the commands mark, unmark or delete
     * @param input the command from the user input
     * @param isMark boolean that checks whether the command is 'mark'
     * @return int index of the command
     */
    public int getMarkNum(String input, boolean isMark) {
        assert input.length() > 0;
        if (isMark) {
            return Integer.valueOf(input.substring(5));
        } else {
            return Integer.valueOf(input.substring(7));
        }
    }

    /**
     * Returns the keyword from the command
     * @param input the command from the user input
     * @return keyword
     */
    public String getKeyword(String input) {
        assert input.length() > 0;
        return input.substring(5);
    }

    /**
     * Returns a description of a Todo object
     * @param input todo command
     * @return Description of the todo object 
     */
    public String getTodoDescription(String input) {
        assert input.length() > 0;
        if (input.substring(4).equals("")) {
            return "";
        }
        return input.substring(5);
    }

    /**
     * Returns the deadline of a Deadline Object
     * @param input deadline command
     * @return deadline of the Deadline object
     */
    public String getDeadlineby(String input) {
        assert input.length() > 0;
        if (input.indexOf("/by") == -1) {
            return "";
        }
        return input.substring(input.indexOf("/by") + 4);
    }

    /**
     * Returns the description of a deadline object
     * @param input deadline command
     * @return description of the deadline object
     */
    public String getDeadlineDescription(String input) {
        assert input.length() > 0;
        if (input.indexOf("/by") == 9) {
            return "";
        }
        return input.substring(9, input.indexOf("/by") - 1);
    }

    /**
     * Returns the description of an event object
     * @param input event command
     * @return description of the event object
     */
    public String getEventDescription(String input) {
        assert input.length() > 0;
        String[] arr = input.split(" ");
        if (arr.length == 1) {
            return "";
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == "/from") {
                    return String.join(" ", Arrays.copyOfRange(arr, 1, i + 1));
                }
            }
            return "";
        }
    }

    /**
     * Returns the event start date and time
     * @param input event command
     * @return Start date and time of an event
     */
    public String getEventFrom(String input) {
        assert input.length() > 0;
        int start = input.indexOf("/from");
        int end = input.indexOf("/to");
        if (start == -1 || end == -1) {
            return "";
        }
        return input.substring(start + 6, end - 1);
    }

    /**
     * Returns the event end date and time
     * @param input event command
     * @return End date and time of an event
     */
    public String getEventEnd(String input) {
        assert input.length() > 0;
        return input.substring(input.indexOf("/to") + 4);
    }
}
