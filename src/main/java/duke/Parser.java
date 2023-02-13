package duke;

/**
 * Parser to parse the command
 */
public class Parser {
    /**
     * Gets the index of the task needed to mark/unmark
     * @param input The mark/unmark command
     * @param isMark Whether the command is a mark command or an unmark command
     * @return The index of the task
     */
    public Integer getMarkNum(String input, boolean isMark) {
        if (isMark) {
            return Integer.valueOf(input.substring(5));
        } else {
            return Integer.valueOf(input.substring(7));
        }
    }

    /**
     * Gets the description of the todo command
     * @param input The todo command
     * @return Description of the todo command
     */
    public String getTodoName(String input) {
        if (input.substring(4).equals("")) {
            return "";
        }
        return input.substring(5);
    }

    /**
     * Gets the deadline of the deadline command
     * @param input The deadline command
     * @return The deadline of the deadline command
     */
    public String getDeadlineDl(String input) {
        if (input.indexOf("/by") == -1) {
            return "";
        }
        return input.substring(input.indexOf("/by") + 4);
    }

    /**
     * Gets the description of the deadline command
     * @param input The deadline command
     * @return Description of the deadline command
     */
    public String getDeadlineName(String input) {
        if (input.indexOf("/by") == 9) {
            return "";
        }
        return input.substring(9, input.indexOf("/by") - 1);
    }

    /**
     * Gets the start time/date of the event command
     * @param input The event command
     * @return The start time/date of the event command
     */
    public String getEventStart(String input) {
        int start = input.indexOf("/from");
        int end = input.indexOf("/to");
        if (start == -1 || end == -1) {
            return "";
        }
        return input.substring(start + 6, end - 1);
    }

    /**
     * Gets the end time/date of the event command
     * @param input The event command
     * @return The end time/date of the event command
     */
    public String getEventEnd(String input) {
        return input.substring(input.indexOf("/to") + 4);
    }

    /**
     * Gets the description of the event command
     * @param input The event command
     * @return Description of the event command
     */
    public String getEventName(String input) {
        int start = input.indexOf("/from");
        if (start == 6) {
            return "";
        }
        return input.substring(6, start - 1);
    }

    /**
     * Gets the index of the task to be deleted
     * @param input The delete command
     * @return Index of the task to be deleted
     */
    public Integer getDeleteNum(String input) {
        return Integer.valueOf(input.substring(7));
    }

    public String getKeyword(String input) {
        return input.substring(5);
    }
}
