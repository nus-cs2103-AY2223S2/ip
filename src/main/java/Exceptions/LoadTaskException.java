package exceptions;

public class LoadTaskException extends DukeException {
    public LoadTaskException() {
        super("OH NO! There was some issue with the previous list! \nDuke was unable to load it... :(");
    }
}
