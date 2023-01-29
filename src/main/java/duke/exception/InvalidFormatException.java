package duke.exception;

public class InvalidFormatException extends DukeException{
    private String message;

    public InvalidFormatException() {
        this.message = "Wrong format. Please try again";
    }

    public InvalidFormatException(String message) {
        this.message = "Wrong format. The correct format is :\n" + message;
    }

    public String getMessage() {
        return this.message;
    }
}
