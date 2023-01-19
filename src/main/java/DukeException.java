public class DukeException extends Exception{
    DukeException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Duke Exception: ";
    }
}
