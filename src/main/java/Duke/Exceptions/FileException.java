package Duke.Exceptions;

public class FileException extends DukeMainExceptions {
    private static final String ERRORMSG = "There is an error in importing the file";

    public FileException() {
        super(ERRORMSG);
    }
    @Override
    public String toString() {
        return ERRORMSG;
    }
}
