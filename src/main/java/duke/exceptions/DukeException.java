package duke.exceptions;

public class DukeException extends Exception{
    String message;
    public DukeException(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}

