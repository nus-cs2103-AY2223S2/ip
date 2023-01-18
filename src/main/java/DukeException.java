public class DukeException extends Exception {
    private final String message;

    /**
     * Constructor for DukeException.
     * @param message A string that describes the error.
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("[Error Encountered] %s", message);
    }
}
