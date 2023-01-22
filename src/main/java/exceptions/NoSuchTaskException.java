package exceptions;

public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}
