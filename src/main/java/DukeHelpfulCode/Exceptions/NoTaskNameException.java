package DukeHelpfulCode.Exceptions;

public class NoTaskNameException extends DukeException{
    public NoTaskNameException(){
        super("Sorry, what's the name of this Task?\n");
    }
}
