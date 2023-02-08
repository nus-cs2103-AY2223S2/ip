package DukeHelpfulCode.Exceptions;

public class NoTaskTypeException extends DukeException{
    public NoTaskTypeException(){
        super("Sorry, what Task is this?\n(todo, deadline, event)\n");
    }
}
