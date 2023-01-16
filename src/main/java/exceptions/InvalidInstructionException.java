package exceptions;

/**
 * Exception class handles if an inappropriate command is given
 */

public class InvalidInstructionException extends DukeException{

    public InvalidInstructionException(){
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}