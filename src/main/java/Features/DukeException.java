package Features;

public class DukeException extends Exception{
    protected String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String printErrorMessage() {
        return this.errorMessage;
    }

}
