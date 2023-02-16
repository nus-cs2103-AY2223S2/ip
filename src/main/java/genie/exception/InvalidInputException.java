package genie.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Hmm, I'm not quite sure what that means... Enter the 'help' command if you need more guidance!");
    }
}