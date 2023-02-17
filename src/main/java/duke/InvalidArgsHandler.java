package duke;


/**
 * This is a class to handle the invalid number of arguments for each of the class
 */
public class InvalidArgsHandler {
    String[] replies;
    String reply;

    /**
     * This is a constructor to handle invalid arguments for Event and Deadline
     * @param replies An array containing the description and dates
     */
    public InvalidArgsHandler(String[] replies){
        this.replies = replies;
    }

    /**
     * This is a constructor to handle invalid arguments for Todo
     * @param reply A string representing the description of Todo
     */
    public InvalidArgsHandler(String reply) {
        this.reply = reply;
    }

    /**
     * Handles the number of arguments for Event
     * @param replies
     * @throws DukeException
     */
    public void checkForEvent(String[] replies) throws DukeException {
        if (replies.length != 2) {
            throw new DukeException(" OOPS!!! The description of a event cannot be empty.");
        }
    }

    /**
     * Handles the number of arguments for Todo
     * @param replies
     * @throws DukeException
     */
    public void checkForToDo(String replies) throws DukeException {
        if (replies.length() < 1) {
            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handles the number of arguments for deadline
     * @param replies
     * @throws DukeException
     */
    public void checkForDeadline(String[] replies) throws DukeException {
        if (replies.length != 2) {
            throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Handles Strings that do not contain deadline, todo, list, event, mark, unmark
     * @throws DukeException
     */
    public void checkForRandomWords(String replies) throws DukeException {
        if (!(toString().startsWith("deadline") || toString().startsWith("todo") || toString().startsWith("list")
                || toString().startsWith("event") || toString().startsWith("mark") || toString().startsWith("unmark") || toString().startsWith("find") ||
                toString().startsWith("tag") || toString().startsWith("delete"))) {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


}
