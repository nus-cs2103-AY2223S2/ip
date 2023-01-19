public class NoTaskException extends HomieException {
    public NoTaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "    > Chu gotta put a task number";
    }
}
