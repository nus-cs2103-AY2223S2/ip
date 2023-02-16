package duke;

/**
 * Handles the processing of inputs in the terminal through string processing
 * @author oliverloo
 * @version 1.0
 *
 */
public class Parser {
    /* Takes in an input,
    1. looks at first word and checks if it is a task
    2. if it is, splits words future
     */

    /**
     * Given an event task string, parses and returns an Event object
     * @param input
     * @return event object, formatted with description and time period
     */
    public static Event parseEvent(String input) throws DukeException {
        String event = input.split(" ", 2)[1];
        String[] event_Arr = event.split(" /from", 2);

        if (event_Arr.length == 2) {
            String content = event_Arr[0];
            String[] period_Arr = event_Arr[1].split(" /to");
            if (period_Arr.length == 2) {
                String from = period_Arr[0].trim();
                String to = period_Arr[1].trim();
                System.out.println(from);
                System.out.println(to);
                return new Event(content, from.trim(), to.trim());
            } else {
                throw new DukeException("Invalid Input! You need to specify a /from and /to or content is empty!");
            }
        } else {
            throw new DukeException("Invalid Input! You need to specify a /from and /to or content is empty!");
        }
    }

    public static int parseMark(String input) {
        int taskNumber = Integer.parseInt(input.substring(input.length() - 1)) - 1;
        return taskNumber;
    }

    /**
     * Given a delete/marked/unmarked command returns the index of that command
     * @param input
     * @return returns the index of that command as an int type
     */
    public static int getIndex(String input) throws DukeException {

        String int_Str = input.split(" ", 2)[1];
        try {
            return Integer.parseInt(int_Str) - 1;
        } catch(Exception e){
            throw new DukeException("Not a valid index!");
        }
    }

    public static int getTaskNum(String input) throws DukeException {

        String int_Str = input.split(" ")[1];
        try {
            int index = Integer.parseInt(int_Str);
            return index;
        } catch(Exception e){
            throw new DukeException("Not a valid index!");
        }
    }

    /**
     * Given a deadline task , parses and returns an Deadline object
     * @param input
     * @return deadline object, formatted with description and time period
     */
    public static Deadline parseDeadline(String input) throws DukeException {
        String deadline = input.split(" ", 2)[1];
        String[] deadline_Arr = deadline.split(" /by");
        if (deadline_Arr.length == 2) {
            String content = deadline_Arr[0];
            String date = deadline_Arr[1];
            return new Deadline(content, date);

        } else {
            throw new DukeException("Duke.Deadline Input Error! You need to specify date or content is empty!");
        }
    }

    /**
     * Takes a line input extracts the todo task description
     * @param input
     * @return description of the todo task
     */
    public static Todo parseTodo(String input) {
        String todo = input.split(" ", 2)[1];
        return new Todo(todo);
    }

    public static String parseSnooze(String input) {
        return (input.split(" /to", 2)[1]).trim();
    }

    /**
     * Checks if input is todo task command
     * @param input
     * @return true if input is todo task , else false
     */
    public static boolean isTodo(String input) {

        assert input.length() > 0 && input != null : "string input should not be empty";
            String[] firstword_Arr = input.trim().split(" ", 2);
            if (firstword_Arr.length == 2) {
                if (firstword_Arr[0].equalsIgnoreCase("todo")) {
                    return true;
                }
            }
            return false;
    }

    /**
     * Checks if input is event command
     * @param input
     * @return true if input is event task, else false
     */
    public static boolean isEvent(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("event")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if input is deadline command
     * @param input
     * @return true if input is deadline, else false
     */
    public static boolean isDeadline(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("deadline")) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if input is mark command
     * @param input
     * @return true if input is mark, else false
     */
    public static boolean isMark(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("mark")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFind(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("find")) {
                return true;
            }
        }
        return false;
    }

    public static String getFindable(String input) {
        return (input.split(" ", 2)[1]).trim();
    }

    public static boolean isUnmark(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("unmark")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSnooze(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("snooze")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if input is list command
     * @param input
     * @return true if input is list, else false
     */
    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }

    /**
     * Checks if input is bye command
     * @param input
     * @return true if input is bye, else false
     */
    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Checks if input is delete command
     * @param input
     * @return true if input is delete, else false
     */
    public static boolean isDelete(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2) {
            if (firstword_Arr[0].equalsIgnoreCase("delete")) {
                return true;
            }
        }
        return false;
    }
}
