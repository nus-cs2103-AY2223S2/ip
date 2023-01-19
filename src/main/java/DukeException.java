public class DukeException extends Exception {
    private String errorMsg;
    public DukeException(String message) {
        super(message);
        this.errorMsg = message;
    }
    public String toString() {
        return this.errorMsg;
    }
}
