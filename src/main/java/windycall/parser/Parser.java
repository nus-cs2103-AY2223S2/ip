package windycall.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.util.Pair;
import windycall.Handler.AddDeadlineHandler;
import windycall.Handler.AddEventHandler;
import windycall.Handler.AddTodoHandler;
import windycall.OperationType;
import windycall.exception.WindyCallException;

/**
 * Deals with making sense of the user command by splitting
 * user command and translation
 */
public class Parser {

    public Parser() {

    }

    /**
     * Returns an operation type representing what users want to do
     *
     * @param command command input by users
     * @return a enumeration type representing a specific operation
     */
    public OperationType getOperationType(String command) {
        if (command.equals("list")) {
            return OperationType.LIST;
        }
        String[] parts = command.split(" ");
        switch (parts[0]) {
        case "mark":
            return OperationType.MARK;
            //break;
        case "unmark":
            return OperationType.UNMARK;
            //break;
        case "delete":
            return OperationType.DELETE;
            //break;
        case "find":
            return OperationType.FIND;
            //break;
        case "tag":
            return OperationType.TAG;
            //break;
        default:
            return OperationType.ADDTASK;
            //break;
        }
    }

    /**
     * Returns the translated index of mark operation while
     * handling invalid input
     *
     * @param parts parts of String of user command split by space
     * @return an index representing which task user want to mark
     */
    public Pair<Integer, String> getMarkIndex(String[] parts) {
        if (parts.length == 1) {
            return new Pair<>(-1, "You should input a number to mark/unmark a task");
        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return new Pair<>(-1, "OOPS!!! You should input a number");
        }
        return new Pair<>(num, num != -1 ? "Success" : "Sorry, your index is out of range");
    }

    public Pair<Integer, String> getUnmarkIndex(String[] parts) {
        if (parts.length == 1) {
            return new Pair<>(-1, "You should input a number to mark/unmark a task");

        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return new Pair<>(-1, "OOPS!!! You should input a number");
        }
        return new Pair<>(num, num != -1 ? "Success" : "Sorry, your index is out of range");
    }

    public Pair<Integer, String> getDeleteIndex(String[] parts) {
        if (parts.length == 1) {
            return new Pair<>(-1, "You should input a number to delete a task");

        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return new Pair<>(-1, "OOPS!!! You should input a number");
        }
        return new Pair<>(num, num != -1 ? "Success" : "Sorry, your index is out of range");
    }

    public Pair<Integer, String> getTagIndex(String[] parts) {
        if (parts.length == 1) {
            return new Pair<>(-1, "You should input the index of task to add a tag");
        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return new Pair<>(-1, "OOPS!!! You should input a number");
        }
        if (parts.length > 2) {
            return new Pair<>(num, num != -1 ? parts[2] : "Sorry, your index is out of range");
        } else {
            return new Pair<>(-1, "Please input a string you want to tag this task");
        }
    }

    public OperationType getAddTaskType(String message) throws WindyCallException {
        String[] parts = message.split(" ");
        switch (parts[0]) {
        case "todo":
            return OperationType.TODO;
//            break;
        case "deadline":
            return OperationType.DEADLINE;
//            break;
        case "event":
            return OperationType.EVENT;
//            break;
        default:
            throw new WindyCallException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//          break;
        }
    }

    /**
     * Return formatted LocalDate by translating user input String deadline
     *
     * @param deadline String representation of date input by user
     * @return formatted LocalDate
     */
    public static LocalDate processDate(String deadline) throws WindyCallException {
        // allowed date format:
        // YYYY-MM-DD or DD/MM/YYYY
        deadline = deadline.trim();
        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            try {
                String[] parts = deadline.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);
                dateTime = LocalDate.of(year, month, day);
            } catch (Exception e1) {
                throw new WindyCallException("Invalid date format. Only YYYY-MM-DD or DD/MM/YYYY are allowed");
            }
        }
        return dateTime;
    }

    /*
    public String getTodoTaskInformation(String command) {
        return "";
    }

    public String[] getDeadlineInformation(String command) {
    }

    public String getEventInformation(String command) {

    }
     */

}
