package duke;


/**
 * This is a class to handle the invalid number of arguments for each of the class
 */
public class handleInvalidArgs {
    String[] replies;
    String reply;

    /**
     * This is a constructor to handle invalid arguments for ToDo
     * @param replies
     */
    public handleInvalidArgs(String[] replies){
        this.replies = replies;
    }

    /**
     * This is a constructor to handle invalid arguments for Event and Deadline
     * @param reply
     */
    public handleInvalidArgs(String reply) {
        this.reply = reply;
    }

    /**
     * Handles the number of arguments for Event
     * @param replies
     * @throws DukeException
     */
    public void checkForEvent(String[] replies) throws DukeException {
        if (replies.length != 3) {
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
            throw new DukeException(" OOPS!!! The description of a deadli8ne cannot be empty.");
        }
    }

    /**
     * Handles  words that are not deadline,todo,list,event,mark,unmark
     * @throws DukeException
     */
    public void checkForRandomWords(String replies) throws DukeException {
        if (!(toString().startsWith("deadline") || toString().startsWith("todo") || toString().startsWith("list")
                || toString().startsWith("event") || toString().startsWith("mark") || toString().startsWith("unmark"))) {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


}
