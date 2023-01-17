package DukeBot.Exception.IncludeExceptions;

public class IncludeToAndFromException extends IncludeException{
    public IncludeToAndFromException() {
        super("\n    ____________________________________________________________\n" +
                "     Error! Did you forget to include the \"/from\" and \"/to\" in your command?"  + "\n" +
                "    ____________________________________________________________\n");
    }
}
