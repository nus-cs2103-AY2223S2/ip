package DukeHelpfulCode.Exceptions;

public class NoSuchTaskException extends DukeException{
    public NoSuchTaskException(){
        super("sorry, but I can't find this Task.");
    }
}
