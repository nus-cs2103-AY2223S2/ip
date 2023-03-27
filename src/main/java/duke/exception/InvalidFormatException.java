package duke.exception;

public class InvalidFormatException extends DukeException{

    public InvalidFormatException() {
        this.message = "Wrong format. Please try again";
    }

    public InvalidFormatException(String message) {
        this.message = "Wrong format. The correct format is :\n" + message;
    }
}
