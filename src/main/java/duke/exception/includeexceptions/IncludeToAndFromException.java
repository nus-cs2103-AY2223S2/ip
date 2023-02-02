package duke.exception.includeexceptions;

public class IncludeToAndFromException extends IncludeException{
    public IncludeToAndFromException() {
        super("\n"
                + "     Error! Did you forget to include the \"/from\" and \"/to\" in your command?" + "\n");
    }
}
