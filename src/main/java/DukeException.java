public class DukeException extends Exception{
    protected String ErrorMessage;

    public DukeException(String ErrorMessage) {
        super(ErrorMessage);
        this.ErrorMessage = ErrorMessage;
    }

    public String PrintErrorMessage () {
        return this.ErrorMessage;
    }
}
