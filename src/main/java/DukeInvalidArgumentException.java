public class DukeInvalidArgumentException extends DukeException{

    public DukeInvalidArgumentException(String message) {
        super(message);
    }

    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
