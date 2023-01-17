public class handleInvalidArgs {
    String[] replies;
    String reply;

    public handleInvalidArgs(String[] replies){
        this.replies = replies;
    }

    public handleInvalidArgs(String reply) {
        this.reply = reply;
    }

    public void checkForEvent(String[] replies) throws DukeException{
        if (replies.length != 3) {
            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void checkForToDo(String replies) throws DukeException{
        if (replies.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void checkForDeadline(String[] replies) throws DukeException{
        if (replies.length != 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void checkForRandomWords(String replies) throws DukeException{
        if (!(toString().startsWith("deadline") || toString().startsWith("todo") || toString().startsWith("list")
                || toString().startsWith("event") || toString().startsWith("mark") || toString().startsWith("unmark"))) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
