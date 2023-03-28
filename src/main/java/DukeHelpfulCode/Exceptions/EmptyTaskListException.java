package DukeHelpfulCode.Exceptions;

public class EmptyTaskListException extends DukeException{
    public EmptyTaskListException(){
        super("It appears that your Task List is currently Empty!\n");
    }
}
