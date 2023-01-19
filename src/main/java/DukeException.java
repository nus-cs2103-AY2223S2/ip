public class DukeException extends Exception {
    String errorMessage;

    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
