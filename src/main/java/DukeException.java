public class DukeException extends RuntimeException {
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
