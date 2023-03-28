package DukeHelpfulCode.Exceptions;

public class NoStartTimeException extends DukeException{
    public NoStartTimeException(){
        super("Sorry, when is this Event starting?\n");
    }
}
