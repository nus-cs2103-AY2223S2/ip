public class DukeInvalidIndexException extends DukeException{

    public DukeInvalidIndexException(String message) {
        super(message);
    }

    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
