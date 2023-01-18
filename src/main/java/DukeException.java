public class DukeException extends Exception{

    public DukeException(String errorMessage) {
        super(":( Sorry! " + errorMessage +". Please try again.");
    }
}
