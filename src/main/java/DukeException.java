public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Sorry! " + super.getMessage();
    }
}
