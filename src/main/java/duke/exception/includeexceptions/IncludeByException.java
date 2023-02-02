package duke.exception.includeexceptions;

public class IncludeByException extends IncludeException {
    public IncludeByException () {
        super("\n" +
                "     Error! Did you forget to include the \"/by\" in your command?" + "\n");
    }
}
