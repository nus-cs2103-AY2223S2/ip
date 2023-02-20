package DukeHelpfulCode.Exceptions;

public class NoDueTimeException extends DukeException{
    public NoDueTimeException(){
        super("Sorry, when is this due?\n");
    }
}
