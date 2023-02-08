package DukeHelpfulCode.Exceptions;

public class TaskAlrMarkException extends DukeException{
    public TaskAlrMarkException(){
        super("It appears that this Task has already been marked as Done. Would you like to mark it as Not Done instead?\n");
    }
}
