package duke.exception;

public class FileException extends DukeException {
    private final static String STRING = "Error with importing the file";
    public FileException() {
    }

    @Override
    public String toString() {
        return STRING;
    }

    @Override
    public void printStackTrace() {
        System.out.println(STRING);
    }
}
