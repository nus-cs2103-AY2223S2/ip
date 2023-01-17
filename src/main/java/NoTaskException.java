public class NoTaskException extends DukeException {
    public NoTaskException(Throwable err) {
        super("OOPS!!! I'm sorry, but the list is empty currently!", err);
    }
}
