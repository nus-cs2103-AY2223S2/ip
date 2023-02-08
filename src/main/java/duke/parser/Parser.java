package duke.parser;

import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTodoException;
import duke.message.MessageStatus;

/**
 * Used for parsing standard input from the user
 */
public class Parser {


    public Parser() {

    }

    private boolean isMark(String message) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length != 2) {
            return false;
        }
        String action = messageSplit[0];

        return (action.equals("mark") || action.equals("unmark"));
    }

    private boolean isAdd(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        return (action.equals("todo") || action.equals("deadline") || action.equals("event"));
    }

    private boolean isDelete(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        return action.equals("delete");
    }

    private boolean isFind(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        return action.equals("find");
    }

    /**
     * Returns the status of task from user's input
     *
     * @param message Contents of message.
     * @return status of task.
     * @throws InvalidInputException If user input is invalid.
     */
    public MessageStatus process(String message) throws InvalidInputException {

        MessageStatus status;
        if (message.equals("bye")) {
            status = MessageStatus.END;
        } else if (message.equals("list")) {
            status = MessageStatus.LIST;
        } else if (isMark(message)) {
            status = MessageStatus.MARK;
        } else if (isAdd(message)) {
            status = MessageStatus.ADD;
        } else if (isDelete(message)) {
            status = MessageStatus.DELETE;
        } else if (isFind(message)) {
            status = MessageStatus.FIND;
        } else {
            throw new InvalidInputException();
        }

        return status;
    }
}
