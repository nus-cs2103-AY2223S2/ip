package seedu.duke;

/**
 * Class for exceptions that happen in the chatbot.
 * it extends the Exception class
 *
 */
public class DukeException extends Exception{

    /**
     * typical exception to run when the bot doesn't understand the command.
     */
    public DukeException(){
        super("    -------------------------------------------\n    " +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n    " +
                "-------------------------------------------");
    }
}
