public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    //formatting
    @Override
    public String getMessage() {
        return super.getMessage() + "\n";
    }
}
