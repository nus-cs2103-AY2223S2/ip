public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
