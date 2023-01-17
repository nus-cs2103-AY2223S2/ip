package DukeBot.Exception.IncludeExceptions;

public class IncludeByException extends IncludeException {
    public IncludeByException () {
        super("\n    ____________________________________________________________\n" +
                "     Error! Did you forget to include the \"/by\" in your command?"  + "\n" +
                "    ____________________________________________________________\n");
    }
}
