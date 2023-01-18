public class DukeException extends Exception {
    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("[Error Encountered] %s", message);
    }
}
